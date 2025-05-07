package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.model.DetalleVenta;
import com.example.inventorypharmacy.model.Precio;
import com.example.inventorypharmacy.model.Venta;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private DetalleVentaRepository detalleVentaRepo;

    @Autowired
    private PrecioRepository precioRepo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    @Override
    public Map<String, Object> getResumenDashboard() {
        Map<String, Object> resumen = new HashMap<>();

        // Total de ventas registradas
        long totalVentas = ventaRepo.count();

        // Total de ingresos (sumatoria de ventas)
        double totalIngresos = ventaRepo.findAll().stream()
                .mapToDouble(Venta::getTotal)
                .sum();

        // Productos con stock menor a 10
        long productosBajoStock = productoRepo.findAll().stream()
                .filter(p -> p.getStock() < 10)
                .count();

        // Productos por vencer en los próximos 30 días
        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(30);
        long productosPorVencer = precioRepo.findAll().stream()
                .filter(p -> p.getFecha_vigencia() != null && p.getFecha_vigencia().isBefore(limite))
                .count();

        // Valor del inventario (stock * precio actual)
        double valorInventario = productoRepo.findAll().stream()
                .mapToDouble(p -> {
                    Double precio = precioRepo
                            .findPrecioActualByProductoId(p.getIdProducto())
                            .stream()
                            .findFirst()
                            .map(Precio::getPrecio_unitario)
                            .orElse(0.0);
                    return p.getStock() * precio;
                }).sum();

        resumen.put("totalVentas", totalVentas);
        resumen.put("totalIngresos", totalIngresos);
        resumen.put("productosBajoStock", productosBajoStock);
        resumen.put("productosPorVencer", productosPorVencer);
        resumen.put("valorInventario", valorInventario);

        return resumen;
    }

    @Override
    public List<Map<String, Object>> getVentasPorCategoria() {
        List<DetalleVenta> detalles = detalleVentaRepo.findAll();

        return detalles.stream()
                .collect(Collectors.groupingBy(
                        d -> d.getProducto().getCategoria().getNombre(),
                        Collectors.summingDouble(DetalleVenta::getSubtotal)
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("categoria", entry.getKey());
                    map.put("monto", entry.getValue());
                    return map;
                })
                .toList();
    }
}

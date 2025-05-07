package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.ResumenVentaDTO;
import com.example.inventorypharmacy.dto.VentaDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ProductoRepository productoRepository;

    private VentaDTO toDTO(Venta v) {
        return new VentaDTO(
                v.getIdVenta(), v.getFecha(), v.getTotal(),
                v.getTipoVenta(),
                v.getCliente() != null ? v.getCliente().getIdCliente() : null,
                v.getUsuario().getIdUsuario());
    }

    private Venta toEntity(VentaDTO dto) {
        Venta v = new Venta();
        v.setIdVenta(dto.getIdVenta());
        v.setFecha(dto.getFecha());
        v.setTotal(dto.getTotal());
        v.setTipoVenta(dto.getTipoVenta());
        v.setUsuario(usuarioRepo.findById(dto.getIdUsuario()).orElseThrow());
        if (dto.getIdCliente() != null)
            v.setCliente(clienteRepo.findById(dto.getIdCliente()).orElse(null));
        return v;
    }

    @Override
    public List<VentaDTO> listarTodas() {
        return ventaRepo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public VentaDTO obtenerPorId(Long id) {
        return ventaRepo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public VentaDTO guardar(VentaDTO dto) {
        Venta saved = ventaRepo.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public void eliminar(Long id) {
        ventaRepo.deleteById(id);
    }

    @Override
    public List<VentaDTO> obtenerReporteDiario() {
        LocalDate hoy = LocalDate.now();
        return listarTodas().stream()
                .filter(venta -> venta.getFecha().isEqual(hoy))
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> obtenerReporteMensual() {
        LocalDate hoy = LocalDate.now();
        return listarTodas().stream()
                .filter(venta -> venta.getFecha().getMonth() == hoy.getMonth() &&
                        venta.getFecha().getYear() == hoy.getYear())
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaDTO> obtenerReporteAnual() {
        int anioActual = LocalDate.now().getYear();
        return listarTodas().stream()
                .filter(venta -> venta.getFecha().getYear() == anioActual)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> obtenerStockPorCategoria() {
        // Lógica para convertir List<Map<String, Object>> a Map<String, Integer>
        List<Map<String, Object>> resultados = productoRepository.obtenerStockPorCategoria();
        return resultados.stream().collect(Collectors.toMap(
                r -> (String) r.get("categoria"), // Clave: nombre de la categoría
                r -> ((Number) r.get("totalStock")).intValue() // Valor: total de stock convertido a Integer
        ));
    }

    @Override
    public double obtenerTotalVentas() {
        return listarTodas().stream()
                .mapToDouble(VentaDTO::getTotal)
                .sum();
    }

    @Override
    public List<Map<String, Object>> obtenerProductosConStockBajo(int limiteStock) {
    // Lógica para obtener productos con stock menor al límite
    return productoRepository.findAll().stream()
            .filter(producto -> producto.getStock() < limiteStock)
            .map(producto -> {
                Map<String, Object> productoMap = new HashMap<>();
                productoMap.put("idProducto", producto.getIdProducto());
                productoMap.put("nombre", producto.getNombre());
                productoMap.put("stock", producto.getStock());
                return productoMap;
            })
            .collect(Collectors.toList());
    }
    @Override
    public List<Venta> getVentasByCliente(Long idCliente) {
        return ventaRepo.findAllByClienteIdCliente(idCliente);
    }

    @Override
    public List<Venta> getVentasByUsuario(Long idUsuario) {
        return ventaRepo.findAllByUsuarioIdUsuario(idUsuario);
    }
    @Override
    public List<VentaDTO> getAllVentas() {
        // Recuperar todas las ventas
        List<Venta> ventas = ventaRepo.findAll();
        return ventas.stream()
                .map(venta -> new VentaDTO(
                        venta.getIdVenta(),
                        venta.getFecha(),
                        venta.getTotal(),
                        venta.getTipoVenta(),
                        venta.getCliente() != null ? venta.getCliente().getIdCliente() : null, // Obtener idCliente
                        venta.getUsuario() != null ? venta.getUsuario().getIdUsuario() : null)) // Obtener idUsuario
                .collect(Collectors.toList());
    }
    @Autowired
    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepo = ventaRepository;
    }

    @Override
    public List<ResumenVentaDTO> obtenerResumenVentas() {
        return ventaRepo.obtenerResumenVentas();
    }
}
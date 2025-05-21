package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.DetalleVentaDTO;
import com.example.inventorypharmacy.dto.ResumenVentaDTO;
import com.example.inventorypharmacy.dto.VentaDTO;
import com.example.inventorypharmacy.dto.VentaRequestDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.VentaService;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private DetalleVentaRepository detalleVentaRepo;

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

    @Override
    public Long registrarVenta(VentaRequestDTO dto) {
        // 1. Obtener cliente y usuario
        Cliente cliente = clienteRepo.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Usuario usuario = usuarioRepo.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Crear la venta
        Venta venta = new Venta();
        venta.setFecha(LocalDate.now()); // 👈 Asegúrate de que esté antes de guardar
        venta.setCliente(cliente);
        venta.setUsuario(usuario);
        venta.setTipoVenta(dto.getTipoVenta());
        venta.setTotal(dto.getTotal());

        Venta ventaGuardada = ventaRepo.save(venta);

        // 3. Guardar los detalles
        for (DetalleVentaDTO d : dto.getDetalle()) {
            Producto producto = productoRepository.findById(d.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleVenta detalle = new DetalleVenta();

            // 👇 Seteamos ID compuesto
            DetalleVentaId id = new DetalleVentaId(ventaGuardada.getIdVenta(), producto.getIdProducto());
            detalle.setId(id);

            detalle.setVenta(ventaGuardada);
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setSubtotal(d.getSubtotal());

            detalleVentaRepo.save(detalle);
        }
        return ventaGuardada.getIdVenta();
    }

    @Override
    public ByteArrayInputStream generarReporteCSV(LocalDate inicio, LocalDate fin) {
        List<Venta> ventas = ventaRepo.findByFechaBetween(inicio, fin);

        final CSVFormat format = CSVFormat.DEFAULT.withHeader("ID", "Fecha", "Cliente", "Tipo Venta", "Total");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter printer = new CSVPrinter(new PrintWriter(out), format)) {

            for (Venta v : ventas) {
                printer.printRecord(
                        v.getIdVenta(),
                        v.getFecha(),
                        v.getCliente() != null ? v.getCliente().getNombre() : "Sin cliente",
                        v.getTipoVenta(),
                        v.getTotal()
                );
            }
            printer.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Error al generar CSV", e);
        }
    }
    @Override
    public ByteArrayInputStream generarReportePDF(LocalDate inicio, LocalDate fin) {
        List<Venta> ventas = ventaRepo.findByFechaBetween(inicio, fin);
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            document.add(new Paragraph("REPORTE DE VENTAS"));
            document.add(new Paragraph("Desde: " + inicio + "   Hasta: " + fin));
            document.add(new Paragraph(" ")); // Espacio

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            // Encabezados
            Stream.of("ID", "Fecha", "Cliente", "Tipo Venta", "Total")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell();
                        cell.setPhrase(new Phrase(header));
                        table.addCell(cell);
                    });

            // Datos
            for (Venta v : ventas) {
                table.addCell(String.valueOf(v.getIdVenta()));
                table.addCell(v.getFecha().toString());
                table.addCell(v.getCliente() != null ? v.getCliente().getNombre() : "Sin cliente");
                table.addCell(v.getTipoVenta());
                table.addCell(String.format("%.2f", v.getTotal()));
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Error al generar PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
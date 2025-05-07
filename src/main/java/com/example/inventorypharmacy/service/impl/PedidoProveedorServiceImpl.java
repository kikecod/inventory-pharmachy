package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.DetallePedidoProveedorDTO;
import com.example.inventorypharmacy.dto.PedidoProveedorRequestDTO;
import com.example.inventorypharmacy.dto.PedidoProveedorResponseDTO;
import com.example.inventorypharmacy.dto.ProductoDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.PedidoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoProveedorServiceImpl implements PedidoProveedorService {

    @Autowired
    private PedidoProveedorRepository pedidoRepo;
    @Autowired
    private DetallePedidoProveedorRepository detalleRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private ProveedorRepository proveedorRepo;

    @Override
    public Long registrarPedido(PedidoProveedorRequestDTO dto) {
        Proveedor proveedor = proveedorRepo.findById(dto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        Usuario usuario = usuarioRepo.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        PedidoProveedor pedido = new PedidoProveedor();
        pedido.setProveedor(proveedor);
        pedido.setUsuario(usuario);
        pedido.setFecha(LocalDate.now());
        pedido.setTotal(dto.getTotal());
        pedido.setEstado("PENDIENTE");

        PedidoProveedor saved = pedidoRepo.save(pedido);

        for (DetallePedidoProveedorDTO d : dto.getDetalle()) {
            Producto producto = productoRepo.findById(d.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetallePedidoProveedor detalle = new DetallePedidoProveedor();
            detalle.setPedido(saved);
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(d.getPrecioUnitario());
            detalle.setSubtotal(d.getSubtotal());

            detalleRepo.save(detalle);
        }

        return saved.getIdPedido();
    }

    @Override
    public List<PedidoProveedorResponseDTO> listarHistorial() {
        return pedidoRepo.findAll().stream().map(pedido -> {
            List<DetallePedidoProveedorDTO> detalles = detalleRepo.findByPedidoIdPedido(pedido.getIdPedido()).stream()
                    .map(det -> new DetallePedidoProveedorDTO(
                            det.getProducto().getIdProducto(),
                            det.getCantidad(),
                            det.getPrecioUnitario(),
                            det.getSubtotal()))
                    .toList();

            return new PedidoProveedorResponseDTO(
                    pedido.getIdPedido(),
                    pedido.getProveedor().getNombre(),
                    pedido.getUsuario().getNombre(),
                    pedido.getFecha(),
                    pedido.getTotal(),
                    pedido.getEstado(),
                    detalles
            );
        }).toList();
    }

    @Override
    public List<ProductoDTO> listarProductosPorProveedor(Long idProveedor) {
        return productoRepo.findAll().stream()
                .filter(p -> p.getProveedor().getIdProveedor().equals(idProveedor))
                .map(p -> new ProductoDTO(
                        p.getIdProducto(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getStock(),
                        p.getUnidad().getIdUnidad(),
                        p.getProveedor().getIdProveedor(),
                        p.getCategoria().getIdCategoria()
                )).toList();
    }
}

package com.example.inventorypharmacy.service.impl;



import com.example.inventorypharmacy.dto.DetalleVentaDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleRepo;

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ProductoRepository productoRepo;

    private DetalleVentaDTO toDTO(DetalleVenta d) {
        return new DetalleVentaDTO(
                d.getVenta().getIdVenta(),
                d.getProducto().getIdProducto(),
                d.getCantidad(),
                d.getSubtotal()
        );
    }

    private DetalleVenta toEntity(DetalleVentaDTO dto) {
        DetalleVenta d = new DetalleVenta();
        d.setVenta(ventaRepo.findById(dto.getIdVenta()).orElseThrow());
        d.setProducto(productoRepo.findById(dto.getIdProducto()).orElseThrow());
        d.setCantidad(dto.getCantidad());
        d.setSubtotal(dto.getSubtotal());
        return d;
    }

    @Override
    public List<DetalleVentaDTO> listarPorVenta(Long idVenta) {
        return detalleRepo.findByVentaIdVenta(idVenta).stream().map(this::toDTO).toList();
    }

    @Override
    public DetalleVentaDTO guardar(DetalleVentaDTO dto) {
        return toDTO(detalleRepo.save(toEntity(dto)));
    }

    @Override
    public void eliminar(Long idVenta, Long idProducto) {
        detalleRepo.deleteById(new DetalleVentaId(idVenta, idProducto));
    }
}

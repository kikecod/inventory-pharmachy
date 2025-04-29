package com.example.inventorypharmacy.service.impl;



import com.example.inventorypharmacy.dto.FacturaDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepo;

    @Autowired
    private VentaRepository ventaRepo;

    private FacturaDTO toDTO(Factura f) {
        return new FacturaDTO(
                f.getIdFactura(),
                f.getVenta().getIdVenta(),
                f.getRfcCliente(),
                f.getRazonSocial(),
                f.getDireccionFiscal(),
                f.getFechaEmision()
        );
    }

    private Factura toEntity(FacturaDTO dto) {
        Factura f = new Factura();
        f.setIdFactura(dto.getIdFactura());
        f.setVenta(ventaRepo.findById(dto.getIdVenta()).orElseThrow());
        f.setRfcCliente(dto.getRfcCliente());
        f.setRazonSocial(dto.getRazonSocial());
        f.setDireccionFiscal(dto.getDireccionFiscal());
        f.setFechaEmision(dto.getFechaEmision());
        return f;
    }

    @Override
    public List<FacturaDTO> listarTodas() {
        return facturaRepo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public FacturaDTO obtenerPorId(Long id) {
        return facturaRepo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public FacturaDTO guardar(FacturaDTO dto) {
        Factura saved = facturaRepo.save(toEntity(dto));
        return toDTO(saved);
    }

    @Override
    public void eliminar(Long id) {
        facturaRepo.deleteById(id);
    }
}

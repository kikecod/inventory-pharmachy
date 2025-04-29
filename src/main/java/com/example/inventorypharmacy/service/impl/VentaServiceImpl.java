package com.example.inventorypharmacy.service.impl;



import com.example.inventorypharmacy.dto.VentaDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    private VentaDTO toDTO(Venta v) {
        return new VentaDTO(
                v.getIdVenta(), v.getFecha(), v.getTotal(),
                v.getTipoVenta(),
                v.getCliente() != null ? v.getCliente().getIdCliente() : null,
                v.getUsuario().getIdUsuario()
        );
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
}
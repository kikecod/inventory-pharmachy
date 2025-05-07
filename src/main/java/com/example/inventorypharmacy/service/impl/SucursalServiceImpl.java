package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.SucursalDTO;
import com.example.inventorypharmacy.model.Sucursal;
import com.example.inventorypharmacy.repository.SucursalRepository;
import com.example.inventorypharmacy.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository repo;

    private SucursalDTO toDTO(Sucursal s) {
        return new SucursalDTO(
                s.getIdSucursal(),
                s.getNombre(),
                s.getDireccion(),
                s.getTelefono(),
                s.getEmail(),
                s.getFechaCreacion()
        );
    }

    private Sucursal toEntity(SucursalDTO dto) {
        return new Sucursal(
                dto.getIdSucursal(),
                dto.getNombre(),
                dto.getDireccion(),
                dto.getTelefono(),
                dto.getEmail(),
                dto.getFechaCreacion()
        );
    }

    @Override
    public List<SucursalDTO> listar() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public SucursalDTO obtenerPorId(Long id) {
        return repo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public SucursalDTO guardar(SucursalDTO dto) {
        return toDTO(repo.save(toEntity(dto)));
    }

    @Override
    public SucursalDTO actualizar(Long id, SucursalDTO dto) {
        if (!repo.existsById(id)) return null;
        dto.setIdSucursal(id);
        return toDTO(repo.save(toEntity(dto)));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
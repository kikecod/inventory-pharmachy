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
        Sucursal sucursal = toEntity(dto);
        sucursal.setFechaCreacion(java.time.LocalDate.now()); // 👈 asigna la fecha actual
        return toDTO(repo.save(sucursal));
    }

    @Override
    public SucursalDTO actualizar(Long id, SucursalDTO dto) {
        Sucursal existente = repo.findById(id).orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        existente.setNombre(dto.getNombre());
        existente.setDireccion(dto.getDireccion());
        existente.setTelefono(dto.getTelefono());
        existente.setEmail(dto.getEmail());
        // ❌ No se toca la fecha de creación

        return toDTO(repo.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
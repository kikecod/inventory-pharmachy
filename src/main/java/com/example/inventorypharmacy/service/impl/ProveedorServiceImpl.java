package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.ProveedorDTO;
import com.example.inventorypharmacy.model.Proveedor;
import com.example.inventorypharmacy.repository.ProveedorRepository;
import com.example.inventorypharmacy.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository repo;

    private ProveedorDTO toDTO(Proveedor p) {
        return new ProveedorDTO(p.getIdProveedor(), p.getNombre(), p.getTelefono(), p.getEmail(), p.getDireccion());
    }

    private Proveedor toEntity(ProveedorDTO dto) {
        return new Proveedor(dto.getIdProveedor(), dto.getNombre(), dto.getTelefono(), dto.getEmail(), dto.getDireccion());
    }

    @Override
    public List<ProveedorDTO> listar() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public ProveedorDTO obtenerPorId(Long id) {
        return repo.findById(id).map(this::toDTO).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    @Override
    public ProveedorDTO guardar(ProveedorDTO dto) {
        return toDTO(repo.save(toEntity(dto)));
    }

    @Override
    public ProveedorDTO actualizar(Long id, ProveedorDTO dto) {
        Proveedor existente = repo.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        existente.setNombre(dto.getNombre());
        existente.setTelefono(dto.getTelefono());
        existente.setEmail(dto.getEmail());
        existente.setDireccion(dto.getDireccion());
        return toDTO(repo.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}

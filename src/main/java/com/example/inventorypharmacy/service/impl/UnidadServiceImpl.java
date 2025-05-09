package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.UnidadDTO;
import com.example.inventorypharmacy.model.Unidad;
import com.example.inventorypharmacy.repository.UnidadRepository;
import com.example.inventorypharmacy.service.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadServiceImpl implements UnidadService {

    @Autowired
    private UnidadRepository repo;

    private UnidadDTO toDTO(Unidad u) {
        return new UnidadDTO(u.getIdUnidad(), u.getDescripcion(), u.getUnipcaja());
    }

    private Unidad toEntity(UnidadDTO dto) {
        return new Unidad(dto.getIdUnidad(), dto.getDescripcion(), dto.getUnipcaja());
    }

    @Override
    public List<UnidadDTO> listar() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public UnidadDTO guardar(UnidadDTO dto) {
        return toDTO(repo.save(toEntity(dto)));
    }

    @Override
    public UnidadDTO actualizar(Long id, UnidadDTO dto) {
        if (!repo.existsById(id)) return null;
        dto.setIdUnidad(id);
        return toDTO(repo.save(toEntity(dto)));
    }

    @Override
    public UnidadDTO obtenerPorId(Long id) {
        return repo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
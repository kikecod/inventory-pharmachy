package com.example.inventorypharmacy.service.impl;



import com.example.inventorypharmacy.dto.RolDTO;
import com.example.inventorypharmacy.model.Rol;
import com.example.inventorypharmacy.repository.RolRepository;
import com.example.inventorypharmacy.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    private RolDTO mapToDto(Rol rol) {
        RolDTO dto = new RolDTO();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        return dto;
    }

    private Rol mapToEntity(RolDTO dto) {
        Rol rol = new Rol();
        rol.setId(dto.getId());
        rol.setNombre(dto.getNombre());
        return rol;
    }

    @Override
    public RolDTO crearRol(RolDTO rolDto) {
        Rol rol = mapToEntity(rolDto);
        return mapToDto(rolRepository.save(rol));
    }

    @Override
    public RolDTO obtenerRolPorId(Long id) {
        return rolRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    @Override
    public List<RolDTO> listarRoles() {
        return rolRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RolDTO actualizarRol(Long id, RolDTO rolDto) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        rol.setNombre(rolDto.getNombre());
        return mapToDto(rolRepository.save(rol));
    }

    @Override
    public void eliminarRol(Long id) {
        rolRepository.deleteById(id);
    }
}

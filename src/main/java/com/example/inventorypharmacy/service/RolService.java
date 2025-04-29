package com.example.inventorypharmacy.service;



import com.example.inventorypharmacy.dto.RolDTO;

import java.util.List;

public interface RolService {
    RolDTO crearRol(RolDTO rolDto);
    RolDTO obtenerRolPorId(Long id);
    List<RolDTO> listarRoles();
    RolDTO actualizarRol(Long id, RolDTO rolDto);
    void eliminarRol(Long id);
}

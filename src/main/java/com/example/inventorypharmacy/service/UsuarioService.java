package com.example.inventorypharmacy.service;



import com.example.inventorypharmacy.dto.UsuarioDTO;
import com.example.inventorypharmacy.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listar();
    UsuarioDTO guardar(UsuarioDTO dto);
    UsuarioDTO obtenerPorId(Long id);
    void eliminar(Long id);
    UsuarioDTO obtenerPerfil(String email);

    UsuarioDTO actualizarPerfil(UsuarioDTO usuarioDTO, String email);
    Usuario crearUsuario(Usuario usuario);
}

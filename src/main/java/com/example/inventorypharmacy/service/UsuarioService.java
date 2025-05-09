package com.example.inventorypharmacy.service;



import com.example.inventorypharmacy.dto.UsuarioDTO;
import com.example.inventorypharmacy.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDTO> listar();
    UsuarioDTO guardar(UsuarioDTO dto);
    UsuarioDTO obtenerPorId(Long id);
    void eliminar(Long id);
    UsuarioDTO obtenerPerfil(String email);

    UsuarioDTO actualizarPerfil(UsuarioDTO usuarioDTO, String email);
    Usuario crearUsuario(Usuario usuario);
    UsuarioDTO actualizar(Long id, UsuarioDTO dto);
    void asignarRol(Long idUsuario, String nombreRol);
    void cambiarPassword(Long idUsuario, String nuevaPassword);
    boolean cambiarPasswordAutenticado(String email, String passwordActual, String nuevaPassword);
    Optional<UsuarioDTO> buscarPorEmail(String email);

    List<UsuarioDTO> listarPorRol(String nombreRol);
}

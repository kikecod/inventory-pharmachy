package com.example.inventorypharmacy.service.impl;



import com.example.inventorypharmacy.dto.UsuarioDTO;
import com.example.inventorypharmacy.model.*;
import com.example.inventorypharmacy.repository.*;
import com.example.inventorypharmacy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private RolRepository rolRepo;
    @Autowired
    private UsuarioRepository usuarioRepository;

    private UsuarioDTO toDTO(Usuario u) {
        return new UsuarioDTO(
                u.getIdUsuario(),
                u.getNombre(),
                u.getApellido(),
                u.getEmail(),
                u.getPassword(),
                u.getRol().getId(),
                u.getFechaCreacion());
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Rol rol = rolRepo.findById(dto.getIdRol()).orElseThrow();
        return new Usuario(dto.getIdUsuario(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getEmail(),
                dto.getPassword(),
                rol,
                dto.getFechaCreacion());
    }

    @Override
    public List<UsuarioDTO> listar() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public UsuarioDTO guardar(UsuarioDTO dto) {
        return toDTO(repo.save(toEntity(dto)));
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return toDTO(repo.findById(id).orElseThrow());
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
    @Override
    public UsuarioDTO obtenerPerfil(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapToDTO(usuario);
    }

    @Override
    public UsuarioDTO actualizarPerfil(UsuarioDTO usuarioDTO, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        // Aquí podrías añadir lógica para actualizar la contraseña si es necesario
        // usuario.setPassword(usuarioDTO.getPassword());

        usuarioRepository.save(usuario);

        return mapToDTO(usuario);
    }

    // Mapea el modelo de usuario a usuarioDTO
    private UsuarioDTO mapToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRol().getId(),
                usuario.getFechaCreacion()
        );
    }
}

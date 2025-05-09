package com.example.inventorypharmacy.service.impl;

import com.example.inventorypharmacy.dto.UsuarioDTO;
import com.example.inventorypharmacy.model.Rol;
import com.example.inventorypharmacy.model.Usuario;
import com.example.inventorypharmacy.repository.RolRepository;
import com.example.inventorypharmacy.repository.UsuarioRepository;
import com.example.inventorypharmacy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =====================
    // === CONVERSORES ====
    // =====================
    private UsuarioDTO toDTO(Usuario u) {
        return new UsuarioDTO(
                u.getIdUsuario(),
                u.getNombre(),
                u.getApellido(),
                u.getEmail(),
                u.getRol().getNombre(), // 👈 nombre del rol
                u.getFechaCreacion()
        );
    }

    private Usuario toEntity(UsuarioDTO dto) {
        Rol rol = rolRepo.findByNombre(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no válido: " + dto.getIdRol()));

        Usuario u = new Usuario();
        u.setIdUsuario(dto.getIdUsuario());
        u.setNombre(dto.getNombre());
        u.setApellido(dto.getApellido());
        u.setEmail(dto.getEmail());
        u.setRol(rol);
        u.setFechaCreacion(dto.getFechaCreacion());

        return u;
    }

    // ===========================
    // === CRUD DE USUARIOS ======
    // ===========================

    @Override
    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public UsuarioDTO guardar(UsuarioDTO dto) {
        Usuario usuario = toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return toDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    // =============================
    // === ACTUALIZAR USUARIO ======
    // =============================

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());

        Rol nuevoRol = rolRepo.findByNombre(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no válido: " + dto.getIdRol()));
        usuario.setRol(nuevoRol);

        return toDTO(usuarioRepository.save(usuario));
    }

    // ====================================
    // === CREAR USUARIO DESDE REGISTRO ===
    // ====================================

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    // ===========================
    // === PERFIL AUTENTICADO ===
    // ===========================

    @Override
    public UsuarioDTO obtenerPerfil(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return toDTO(usuario);
    }

    @Override
    public UsuarioDTO actualizarPerfil(UsuarioDTO usuarioDTO, String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());

        usuarioRepository.save(usuario);

        return toDTO(usuario);
    }

    @Override
    public void asignarRol(Long idUsuario, String nombreRol) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepo.findByNombre(nombreRol)
                .orElseThrow(() -> new RuntimeException("Rol no válido: " + nombreRol));

        usuario.setRol(rol);
        usuarioRepository.save(usuario);
    }

    @Override
    public void cambiarPassword(Long idUsuario, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean cambiarPasswordAutenticado(String email, String passwordActual, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(passwordActual, usuario.getPassword())) {
            return false;
        }

        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);
        return true;
    }
    @Override
    public Optional<UsuarioDTO> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(this::toDTO);
    }


    @Override
    public List<UsuarioDTO> listarPorRol(String nombreRol) {
        return usuarioRepository.findAllByRolNombre(nombreRol)
                .stream().map(this::toDTO).toList();
    }
}
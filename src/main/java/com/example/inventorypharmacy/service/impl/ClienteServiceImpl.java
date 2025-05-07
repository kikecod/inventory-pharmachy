package com.example.inventorypharmacy.service.impl;



import com.example.inventorypharmacy.dto.ClienteDTO;
import com.example.inventorypharmacy.model.Cliente;
import com.example.inventorypharmacy.repository.ClienteRepository;
import com.example.inventorypharmacy.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repo;

    private ClienteDTO toDTO(Cliente c) {
        return new ClienteDTO(c.getIdCliente(), c.getCi(), c.getNombre(), c.getApellido(), c.getEmail(), c.getTelefono(),  c.getDireccion());
    }

    private Cliente toEntity(ClienteDTO dto) {
        return new Cliente(dto.getIdCliente(), dto.getCi(), dto.getNombre(), dto.getApellido(),  dto.getEmail(), dto.getTelefono(), dto.getDireccion());
    }

    @Override
    public List<ClienteDTO> listar() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public ClienteDTO guardar(ClienteDTO dto) {
        return toDTO(repo.save(toEntity(dto)));
    }
    @Override
    public ClienteDTO actualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());

        return toDTO(repo.save(cliente));
    }

    @Override
    public ClienteDTO obtenerPorId(Long id) {
        return toDTO(repo.findById(id).orElseThrow());
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }


}

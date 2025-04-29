package com.example.inventorypharmacy.service;



import com.example.inventorypharmacy.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listar();
    ClienteDTO guardar(ClienteDTO dto);
    ClienteDTO obtenerPorId(Long id);
    void eliminar(Long id);
}

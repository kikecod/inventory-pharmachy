package com.example.inventorypharmacy.service;

import com.example.inventorypharmacy.dto.PedidoProveedorRequestDTO;
import com.example.inventorypharmacy.dto.PedidoProveedorResponseDTO;
import com.example.inventorypharmacy.dto.ProductoDTO;

import java.util.List;

public interface PedidoProveedorService {
    Long registrarPedido(PedidoProveedorRequestDTO dto);
    List<PedidoProveedorResponseDTO> listarHistorial();
    List<ProductoDTO> listarProductosPorProveedor(Long idProveedor);
}

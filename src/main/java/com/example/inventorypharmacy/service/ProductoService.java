package com.example.inventorypharmacy.service;


import com.example.inventorypharmacy.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listarTodos();
    ProductoDTO buscarPorId(Long id);
    ProductoDTO guardar(ProductoDTO productoDTO);
    ProductoDTO actualizar(Long id, ProductoDTO productoDTO);
    void eliminar(Long id);
}

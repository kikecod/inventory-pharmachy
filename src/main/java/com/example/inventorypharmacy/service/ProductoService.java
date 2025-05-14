package com.example.inventorypharmacy.service;


import com.example.inventorypharmacy.dto.PrecioDTO;
import com.example.inventorypharmacy.dto.ProductoDTO;
import com.example.inventorypharmacy.dto.ProductoResponseDTO;
import com.example.inventorypharmacy.dto.ProductoSucursalRequestDTO;
import com.example.inventorypharmacy.dto.ProductoSucursalResponseDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listarTodos();
    ProductoDTO buscarPorId(Long id);
    ProductoDTO guardar(ProductoDTO productoDTO);
    ProductoDTO actualizar(Long id, ProductoDTO productoDTO);
    void eliminar(Long id);

    List<ProductoResponseDTO> obtenerProductosConDetalle();
    List<ProductoSucursalResponseDTO> obtenerProductosPorSucursal(Long idSucursal);
    void actualizarPrecio(Long idProducto, PrecioDTO dto);
    ProductoDTO guardarConSucursal(ProductoDTO productoDTO, Long idSucursal);
}

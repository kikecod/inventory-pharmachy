package com.example.inventorypharmacy.controller;



import com.example.inventorypharmacy.dto.ProductoDTO;
import com.example.inventorypharmacy.dto.ProductoResponseDTO;
import com.example.inventorypharmacy.dto.ProductoSucursalResponseDTO;
import com.example.inventorypharmacy.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getProductosConDetalle() {
        return ResponseEntity.ok(productoService.obtenerProductosConDetalle());
    }

    @GetMapping("/{id}")
    public ProductoDTO buscarPorId(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping
    public ProductoDTO guardar(@RequestBody ProductoDTO dto) {
        return productoService.guardar(dto);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizar(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<List<ProductoSucursalResponseDTO>> getProductosPorSucursal(@PathVariable Long idSucursal) {
        List<ProductoSucursalResponseDTO> productos = productoService.obtenerProductosPorSucursal(idSucursal);
        return ResponseEntity.ok(productos);
    }
    @GetMapping("/sucursal")
    public ResponseEntity<List<ProductoSucursalResponseDTO>> obtenerProductosPorSucursal(
            @RequestHeader("Sucursal-ID") Long idSucursal) {
        List<ProductoSucursalResponseDTO> productos = productoService.obtenerProductosPorSucursal(idSucursal);
        return ResponseEntity.ok(productos);
    }

}
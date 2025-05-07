package com.example.inventorypharmacy.controller;

import com.example.inventorypharmacy.dto.PedidoProveedorRequestDTO;
import com.example.inventorypharmacy.dto.PedidoProveedorResponseDTO;
import com.example.inventorypharmacy.dto.ProductoDTO;
import com.example.inventorypharmacy.dto.ProveedorDTO;
import com.example.inventorypharmacy.service.PedidoProveedorService;
import com.example.inventorypharmacy.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "http://localhost:5173/")
public class ProveedorController {

    @Autowired
    private ProveedorService service;
    @Autowired
    private PedidoProveedorService pedidoProveedorService;

    @GetMapping
    public List<ProveedorDTO> listar() {
        return service.listar();
    }

    @PostMapping
    public ProveedorDTO guardar(@RequestBody ProveedorDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping("/{id}")
    public ProveedorDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ProveedorDTO actualizar(@PathVariable Long id, @RequestBody ProveedorDTO dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
    @PostMapping("/pedidos")
    public ResponseEntity<Map<String, Object>> crearPedido(@RequestBody PedidoProveedorRequestDTO dto) {
        Long id = pedidoProveedorService.registrarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("idPedido", id));
    }

    @GetMapping("/historial-compras")
    public ResponseEntity<List<PedidoProveedorResponseDTO>> historialCompras() {
        return ResponseEntity.ok(pedidoProveedorService.listarHistorial());
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<ProductoDTO>> productosPorProveedor(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoProveedorService.listarProductosPorProveedor(id));
    }

    // Los próximos endpoints (pedidos, historial, productos) los agregamos ahora
}
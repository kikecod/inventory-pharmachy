package com.example.inventorypharmacy.dto;



import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProveedorResponseDTO {
    private Long idPedido;
    private String proveedor;
    private String usuario;
    private LocalDate fecha;
    private Double total;
    private String estado;
    private List<DetallePedidoProveedorDTO> detalle;
}
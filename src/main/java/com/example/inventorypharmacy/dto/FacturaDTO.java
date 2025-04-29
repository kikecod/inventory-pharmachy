package com.example.inventorypharmacy.dto;



import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaDTO {
    private Long idFactura;
    private Long idVenta;
    private String rfcCliente;
    private String razonSocial;
    private String direccionFiscal;
    private LocalDate fechaEmision;
}

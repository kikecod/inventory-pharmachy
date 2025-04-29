package com.example.inventorypharmacy.model;



import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaId implements Serializable {
    private Long venta;
    private Long producto;

    // equals y hashCode por clave compuesta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleVentaId)) return false;
        DetalleVentaId that = (DetalleVentaId) o;
        return Objects.equals(venta, that.venta) && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venta, producto);
    }
}

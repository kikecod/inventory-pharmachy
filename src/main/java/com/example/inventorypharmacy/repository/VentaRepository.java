package com.example.inventorypharmacy.repository;

// VentaRepository.java
import com.example.inventorypharmacy.dto.ResumenVentaDTO;
import com.example.inventorypharmacy.dto.VentaDTO;
import com.example.inventorypharmacy.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findAllByClienteIdCliente(Long idCliente);

    List<Venta> findAllByUsuarioIdUsuario(Long idUsuario);
    @Query("""
    SELECT new com.example.inventorypharmacy.dto.ResumenVentaDTO(
        v.idVenta,
        CONCAT(c.nombre, ' ', COALESCE(c.apellido, '')),
        v.fecha,
        CAST(SUM(dv.cantidad) AS integer),
        v.tipoVenta,
        v.total
    )
    FROM Venta v
    LEFT JOIN v.cliente c
    JOIN DetalleVenta dv ON dv.venta = v
    GROUP BY v.idVenta, c.nombre, c.apellido, v.fecha, v.tipoVenta, v.total
    """)
    List<ResumenVentaDTO> obtenerResumenVentas();
}

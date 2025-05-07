package com.example.inventorypharmacy.repository;



import com.example.inventorypharmacy.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCi(String ci);
    boolean existsByCi(String ci);
    boolean existsByEmail(String email);
    boolean existsByCiAndIdClienteNot(String ci, Long idCliente);
    boolean existsByEmailAndIdClienteNot(String email, Long idCliente);
}
package com.example.inventorypharmacy.repository;



import com.example.inventorypharmacy.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
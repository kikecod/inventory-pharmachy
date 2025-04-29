package com.example.inventorypharmacy.repository;


import com.example.inventorypharmacy.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

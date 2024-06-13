package com.example.testsbs.inventario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testsbs.inventario.modelo.Inventario;

public interface InventarioRepositorio extends JpaRepository<Inventario, Long>{
    
}

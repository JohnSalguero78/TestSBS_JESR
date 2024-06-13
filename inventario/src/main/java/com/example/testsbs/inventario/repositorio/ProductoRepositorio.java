package com.example.testsbs.inventario.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testsbs.inventario.modelo.Producto;
@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long>{
    
}

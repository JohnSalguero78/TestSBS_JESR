package com.example.testsbs.inventario.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testsbs.inventario.modelo.Inventario;
import com.example.testsbs.inventario.modelo.Producto;
import com.example.testsbs.inventario.repositorio.InventarioRepositorio;
import com.example.testsbs.inventario.repositorio.ProductoRepositorio;

@RestController
@RequestMapping("/inventario")
public class ControladorInventario {
    
    @Autowired
    private InventarioRepositorio repoInv;

    @Autowired
    private ProductoRepositorio repoProd;

    @GetMapping("/conectar")
    public String index() {
        return "CONECTADO A INVENTARIO";
    }

    @GetMapping
    public List<Inventario> listaInventario() {
        return repoInv.findAll();
    }

    @PostMapping("/crear")
    public String guardar(@RequestBody Inventario inventario) {
        Optional<Producto> optionalProducto = repoProd.findById(inventario.getProducto().getId());
        if (optionalProducto.isPresent()) {
            inventario.setProducto(optionalProducto.get());
            repoInv.save(inventario);
            return "CREADO CORRECTAMENTE";
        } else {
            return "PRODUCTO NO ENCONTRADO";
        }
    }

    @PutMapping("/editar/{id}")
    public String actualizarInventario(@PathVariable("id") Long id, @RequestBody Inventario inventario) {
        Optional<Inventario> optionalInventario = repoInv.findById(id);
        if (optionalInventario.isPresent()) {
            Inventario actualizaInv = optionalInventario.get();
            actualizaInv.setCantidad(inventario.getCantidad());
            actualizaInv.setFecha_ingreso(inventario.getFecha_ingreso());
            
            Optional<Producto> optionalProducto = repoProd.findById(inventario.getProducto().getId());
            if (optionalProducto.isPresent()) {
                actualizaInv.setProducto(optionalProducto.get());
                repoInv.save(actualizaInv);
                return "ACTUALIZADO INVENTARIO CORRECTAMENTE";
            } else {
                return "PRODUCTO NO ENCONTRADO";
            }
        } else {
            return "INVENTARIO NO ENCONTRADO";
        }
    }

    @DeleteMapping("/borrar/{id}")
    public String borrado(@PathVariable("id") Long id) {
        Optional<Inventario> optionalInventario = repoInv.findById(id);
        if (optionalInventario.isPresent()) {
            Inventario borraInv = optionalInventario.get();
            repoInv.delete(borraInv);
            return "BORRADO EXITOSO";
        } else {
            return "INVENTARIO NO ENCONTRADO";
        }
    }
}

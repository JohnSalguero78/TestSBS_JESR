package com.example.testsbs.inventario.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.testsbs.inventario.modelo.Producto;
import com.example.testsbs.inventario.repositorio.ProductoRepositorio;



@RestController
public class ControladorProducto {
    @Autowired
    private ProductoRepositorio repo;
    @GetMapping()
    public String index(){

        return "CONECTADO";
    }
    @GetMapping("producto")
    public List<Producto> listaProducto() {
        return repo.findAll();
    }

    @PostMapping("crear")
    public String guardar(@RequestBody Producto producto) {
        repo.save(producto);
        return "CREADO CORRECTAMENTE";
    }
    @PutMapping("editar/{id}")
    public String actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {

        Producto actualizaPro = repo.findById(id).get();
        actualizaPro.setNombre(producto.getNombre());
        actualizaPro.setDescripcion(producto.getDescripcion());
        actualizaPro.setPrecio(producto.getPrecio());
        repo.save(actualizaPro);
        return "ACTUALIZADO CORRECTAMENTE";
    }

    @DeleteMapping("borrar/{id}")
    public String borrado(@PathVariable Long id){

        Producto borraProd = repo.findById(id).get();
        repo.delete(borraProd);
        return "BORRADO EXITOSO";

    }

    
}

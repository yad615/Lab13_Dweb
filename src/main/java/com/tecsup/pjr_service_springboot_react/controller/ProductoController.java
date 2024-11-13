package com.tecsup.pjr_service_springboot_react.controller;

import com.tecsup.pjr_service_springboot_react.Exception.ResourceNotFoundException;
import com.tecsup.pjr_service_springboot_react.model.Producto;
import com.tecsup.pjr_service_springboot_react.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/productos")
    public List<Producto> ListarProductos(){
        return productoRepository.findAll();
    }

    @PostMapping("/productos")
    public Producto guardarProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> ListarProductoPorId(@PathVariable long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el id: " + id));
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> ActualizarProducto(@PathVariable long id, @RequestBody Producto productoRequest){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el id: " + id));

        producto.setCodigo(productoRequest.getCodigo());
        producto.setNombre(productoRequest.getNombre());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setStock(productoRequest.getStock());
        producto.setCategoria(productoRequest.getCategoria());

        Producto productoActualizado = productoRepository.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarProducto(@PathVariable long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El producto no existe con el id: " + id));

        productoRepository.delete(producto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
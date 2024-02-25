package com.prueba.productosapp.controller;

import com.prueba.productosapp.exception.ProductoNotFoundException;
import com.prueba.productosapp.models.ProductoModel;
import com.prueba.productosapp.services.ProductoService;
import com.prueba.productosapp.util.ResponseObject;
import com.prueba.productosapp.util.ResponseObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        try {
            List<ProductoModel> productos = productoService.getAllProducts();
            ResponseObjects response = new ResponseObjects(HttpStatus.OK.value(), "Consulta exitosa", productos);
            return ResponseEntity.ok().body(response);
        } catch (ProductoNotFoundException e) {
            ResponseObjects response = new ResponseObjects(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> productById(@PathVariable Long id) {
        Optional<ProductoModel> productoOptional = productoService.getProductById(id);
        if (productoOptional.isPresent()) {
            ProductoModel producto = productoOptional.get();
            ResponseObject response = new ResponseObject(HttpStatus.OK.value(), "Producto encontrado", producto);
            return ResponseEntity.ok().body(response);
        } else {
            throw new ProductoNotFoundException("Producto no encontrado con el id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseObject> createAProduct(@Valid @RequestBody ProductoModel producto) {
        try {
            ProductoModel nuevoProducto = productoService.createProduct(producto);
            ResponseObject response = new ResponseObject(HttpStatus.CREATED.value(), "Producto creado exitosamente", nuevoProducto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ResponseObject response = new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al crear el producto: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProductById(@PathVariable Long id, @RequestBody ProductoModel productUpdated) {
        try {
            ProductoModel producto = productoService.updateProduct(id, productUpdated);
            ResponseObject response = new ResponseObject(HttpStatus.OK.value(), "Producto actualizado exitosamente", producto);
            return ResponseEntity.ok().body(response);
        } catch (ProductoNotFoundException e) {
            ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ResponseObject response = new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al actualizar el producto: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProductById(@PathVariable Long id) {
        try {
            productoService.deleteProduct(id);
            ResponseObject response = new ResponseObject(HttpStatus.OK.value(), "Producto eliminado exitosamente", null);
            return ResponseEntity.ok().body(response);
        } catch (ProductoNotFoundException e) {
            ResponseObject response = new ResponseObject(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            ResponseObject response = new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al eliminar el producto: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
package com.prueba.productosapp.services;

import com.prueba.productosapp.exception.InvalidProductPriceException;
import com.prueba.productosapp.exception.ProductNameAlreadyExistsException;
import com.prueba.productosapp.exception.ProductoNotFoundException;
import com.prueba.productosapp.models.ProductoModel;
import com.prueba.productosapp.repository.IUProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProdutoService{

    @Autowired
    private IUProductoRepository productoRepository;

    public List<ProductoModel> getAllProducts() {
        List<ProductoModel> productos = productoRepository.findAll();
        if (productos.isEmpty()) {
            throw new ProductoNotFoundException("No se encontraron productos en el sistema");
        } else {
            return productos;
        }
    }

    public Optional<ProductoModel> getProductById(Long id) {
        return productoRepository.findById(id);
    }

    public ProductoModel createProduct(ProductoModel producto) {

        if (productoRepository.existsByNombre(producto.getNombre())) {
            throw new ProductNameAlreadyExistsException("Ya existe un producto con el nombre: " + producto.getNombre());
        }

        if (producto.getPrecio() <= 0) {
            throw new InvalidProductPriceException("El precio del producto debe ser mayor que cero.");
        }

        return productoRepository.save(producto);
    }

    public ProductoModel updateProduct(Long id, ProductoModel productUpdated) {
        ProductoModel productExisting = productoRepository.findById(id).orElse(null);
        if (productExisting != null) {

            if (productoRepository.existsByNombre(productExisting.getNombre())) {
                throw new ProductNameAlreadyExistsException("Ya existe un producto con el nombre: " + productExisting.getNombre());
            }

            if (productExisting.getPrecio() <= 0) {
                throw new InvalidProductPriceException("El precio del producto debe ser mayor que cero.");
            }

            productExisting.setNombre(productUpdated.getNombre());
            productExisting.setPrecio(productUpdated.getPrecio());
            return productoRepository.save(productExisting);
        } else {
            throw new ProductoNotFoundException("No se encontró ningún producto con el ID: " + id);
        }
    }

    public void deleteProduct(Long id) {
        Optional<ProductoModel> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            productoRepository.deleteById(id);
        } else {
            throw new ProductoNotFoundException("No se encontró el producto con el ID: " + id);
        }
    }
}

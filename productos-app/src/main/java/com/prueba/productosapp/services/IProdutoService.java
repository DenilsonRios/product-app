package com.prueba.productosapp.services;

import com.prueba.productosapp.models.ProductoModel;
import java.util.List;
import java.util.Optional;

public interface IProdutoService {

    List<ProductoModel> getAllProducts();
    Optional<ProductoModel> getProductById(Long id); // Corregido el tipo de retorno
    ProductoModel createProduct(ProductoModel product);
    ProductoModel updateProduct(Long id, ProductoModel updatedProduct);
    void deleteProduct(Long id);
}

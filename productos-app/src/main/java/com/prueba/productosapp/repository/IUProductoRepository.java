package com.prueba.productosapp.repository;

import com.prueba.productosapp.models.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUProductoRepository extends JpaRepository<ProductoModel,Long> {
    boolean existsByNombre(String nombre);
}

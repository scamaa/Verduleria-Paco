package com.verduleria.dao;

import com.verduleria.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto,Long>{
    
}

package com.verduleria.dao;

import com.verduleria.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente,Long> {
    
}

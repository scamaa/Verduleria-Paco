package com.verduleria.dao;

import com.verduleria.domain.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditoDao extends JpaRepository<Credito,Long> {
    
}

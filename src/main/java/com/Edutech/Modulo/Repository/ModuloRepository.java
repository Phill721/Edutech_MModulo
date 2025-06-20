package com.Edutech.Modulo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Edutech.Modulo.Model.Modulo;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
    
}

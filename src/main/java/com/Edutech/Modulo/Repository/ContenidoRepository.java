package com.Edutech.Modulo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Edutech.Modulo.Model.Contenido;

@Repository
public interface ContenidoRepository extends JpaRepository<Contenido, Integer> {
    
}

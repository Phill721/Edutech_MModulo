package com.Edutech.Modulo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edutech.Modulo.Model.Contenido;
import com.Edutech.Modulo.Repository.ContenidoRepository;

@Service
public class ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;

    public Contenido crearContenido(Contenido contenido){
        return contenidoRepository.save(contenido);
    }

    public Optional<Contenido> buscarxid(int id){
        return contenidoRepository.findById(id);
    }

    public Optional<Contenido> actualizarContenido(int id, Contenido updatedContenido){
        return contenidoRepository.findById(id).map(contenido -> {
            contenido.setTitulo(updatedContenido.getTitulo());
            contenido.setTipo(updatedContenido.getTipo());
            contenido.setContenido(updatedContenido.getContenido());
            contenido.setUrl(updatedContenido.getUrl());
            contenido.setFecha(updatedContenido.getFecha());
            contenido.setModulo(updatedContenido.getModulo());
            return contenidoRepository.save(contenido);
        });
    }

    public boolean eliminarContenido(int id){
        if(contenidoRepository.existsById(id)){
            contenidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

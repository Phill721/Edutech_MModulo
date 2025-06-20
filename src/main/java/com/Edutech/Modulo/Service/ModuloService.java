package com.Edutech.Modulo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Edutech.Modulo.Model.Modulo;
import com.Edutech.Modulo.Repository.ModuloRepository;

@Service
public class ModuloService {
    @Autowired
    private ModuloRepository moduloRepository;

    public Modulo crearModulo(Modulo modulo){
        return moduloRepository.save(modulo);
    }

    public Optional<Modulo> buscarxid(int id){
        return moduloRepository.findById(id);
    }
    public Optional<Modulo> actualizarModulo(int id, Modulo updatedModulo){
        return moduloRepository.findById(id).map(modulo -> {
            modulo.setId(updatedModulo.getId());
            modulo.setTitulo(updatedModulo.getTitulo());
            modulo.setDuracionsemanas(updatedModulo.getDuracionsemanas());
            modulo.setOrden(updatedModulo.getOrden());
            modulo.setRecursos(updatedModulo.getRecursos());
            modulo.setEsvisible(updatedModulo.isEsvisible());
            return moduloRepository.save(modulo);
        });
    }

    public boolean eliminarModulo(int id){
        if(moduloRepository.existsById(id)){
            moduloRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean cambiarVisibilidad(int id){
        Optional<Modulo> modulo = moduloRepository.findById(id);
        if(modulo.isPresent()){
            Modulo modactualizar = modulo.get();
            modactualizar.setEsvisible(!modactualizar.isEsvisible());
            moduloRepository.save(modactualizar);
            return true;
        }
        return false;
    }
}

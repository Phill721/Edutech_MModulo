package com.Edutech.Modulo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Edutech.Modulo.Model.Modulo;
import com.Edutech.Modulo.Repository.ModuloRepository;
import com.Edutech.Modulo.Service.ModuloService;

@RestController
@RequestMapping("/api/modulos")
public class ModuloController {
    @Autowired
    private ModuloService moduloService;
    @Autowired
    private ModuloRepository moduloRepository;

    @PostMapping
    public ResponseEntity<Modulo> crearModulo(@RequestBody Modulo modulo){
        Modulo nuevoModulo = moduloService.crearModulo(modulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoModulo);
    }

    @GetMapping
    public List<Modulo> listaModulos(){
        return moduloRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> buscarxid(@PathVariable int id){
        return moduloService.buscarxid(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modulo> actualizarModulo(@PathVariable int id, @RequestBody Modulo nuevoModulo){
        return moduloService.actualizarModulo(id, nuevoModulo)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarModulo(@PathVariable int id){
        moduloService.eliminarModulo(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/modificar/visibilidad")
    public ResponseEntity<Void> cambiarVisibilidad(@PathVariable int id){
        boolean actualizado = moduloService.cambiarVisibilidad(id);
        if(actualizado){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

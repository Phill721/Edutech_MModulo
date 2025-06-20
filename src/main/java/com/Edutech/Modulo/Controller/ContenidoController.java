package com.Edutech.Modulo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Edutech.Modulo.Model.Contenido;
import com.Edutech.Modulo.Repository.ContenidoRepository;
import com.Edutech.Modulo.Service.ContenidoService;

@RestController
@RequestMapping("/api/contenido")
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;
    @Autowired
    private ContenidoRepository contenidoRepository;

    @PostMapping
    public ResponseEntity<Contenido> crearContenido(@RequestBody Contenido contenido){
        Contenido newContenido = contenidoService.crearContenido(contenido);
        return ResponseEntity.status(HttpStatus.CREATED).body(newContenido);
    }

    @GetMapping
    public List<Contenido> listarContenido(){
        return contenidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contenido> buscarxid(@PathVariable int id){
        return contenidoService.buscarxid(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contenido> actualizarContenido(@PathVariable int id, @RequestBody Contenido nuevoContenido){
        return contenidoService.actualizarContenido(id, nuevoContenido)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContenido(@PathVariable int id){
        if(contenidoService.eliminarContenido(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

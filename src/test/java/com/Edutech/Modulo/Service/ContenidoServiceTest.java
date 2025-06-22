package com.Edutech.Modulo.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Edutech.Modulo.Model.Contenido;
import com.Edutech.Modulo.Repository.ContenidoRepository;

public class ContenidoServiceTest {
    @Mock
    private ContenidoRepository contenidoRepository;
    @InjectMocks
    private ContenidoService contenidoService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testcrearContenido(){
        Contenido contenido = new Contenido(0, "Hello world", "Texto",
         "print(''Hello world'')", "www.google.com", null, null);
        Contenido guardado = new Contenido(1, "Hello world", "Texto",
         "print(''Hello world'')", "www.google.com", null, null);
        when(contenidoRepository.save(contenido)).thenReturn(guardado);
        Contenido resultado = contenidoService.crearContenido(contenido);
        assertThat(resultado.getId()).isEqualTo(1);
        verify(contenidoRepository).save(contenido);
    }

    @Test
    void testListarcontenido(){
        Contenido c1 = new Contenido(1, "Hello world", "Texto",
         "print(''Hello world'')", "www.google.com", null, null);
        Contenido c2 = new Contenido(2, "For", "Texto",
         "asdasd", "www.youtube.com", null, null);

        when(contenidoRepository.findAll()).thenReturn(Arrays.asList(c1,c2));
        List<Contenido> resultado = contenidoService.listar();
        assertThat(resultado).hasSize(2).contains(c1,c2);
        verify(contenidoRepository).findAll();
    }

    @Test
    void testEliminarcontenido(){
        int id = 1;
        doNothing().when(contenidoRepository).deleteById(id);
        contenidoService.eliminarContenido(id);
        verify(contenidoRepository).deleteById(id);
    }

    @Test
    void testBuscarxid(){
        int idexistente = 1;
        Contenido contenidosimulado = new Contenido(1, "Hello world", "Texto",
         "print(''Hello world'')", "www.google.com", LocalDate.of(2025, 04, 30), null);
        
        when(contenidoRepository.findById(idexistente)).thenReturn(Optional.of(contenidosimulado));
        Optional<Contenido> resultado = contenidoService.buscarxid(idexistente);
        assertThat(resultado).isPresent().hasValueSatisfying(contenido -> {
            assertThat(contenido.getId()).isEqualTo(idexistente);
            assertThat(contenido.getTitulo()).isEqualTo("Hello world");
            assertThat(contenido.getTipo()).isEqualTo("Texto");
            assertThat(contenido.getContenido()).isEqualTo("print(''Hello world'')");
            assertThat(contenido.getUrl()).isEqualTo("www.google.com");
            assertThat(contenido.getFecha()).isEqualTo(LocalDate.of(2025, 04, 30));
            assertThat(contenido.getModulo()).isEqualTo(null);
        });
        verify(contenidoRepository).findById(idexistente);
    }

    @Test
    void testActualizarcontenido(){
        int id = 1;
        Contenido existente = new Contenido(id, "Hello world", "Texto",
         "print(''Hello world'')", "www.google.com", null, null);
        Contenido actualizado = new Contenido(id, "For", "Texto",
         "asdasd", "www.google.com", null, null);
        
        when(contenidoRepository.findById(id)).thenReturn(Optional.of(existente));
        when(contenidoRepository.save(existente)).thenReturn(existente);
        Optional<Contenido> resultado = contenidoService.actualizarContenido(id, actualizado);
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getTitulo()).isEqualTo("For");
        assertThat(resultado.get().getContenido()).isEqualTo("asdasd");
        verify(contenidoRepository).findById(id);
        verify(contenidoRepository).save(existente);
    }
    
}

package com.Edutech.Modulo.Service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;

import com.Edutech.Modulo.Model.Modulo;
import com.Edutech.Modulo.Repository.ModuloRepository;

public class ModuloServiceTest {
    @Mock
    private ModuloRepository moduloRepository;

    @InjectMocks
    private ModuloService moduloService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearModulo(){
        Modulo modulo = new Modulo(0, "Ciclo While", 1, 1,
         null, true, null, null, null);
        Modulo moduloguardado = new Modulo(1, "Ciclo While", 1, 1,
         null, true, null, null, null);
        when(moduloRepository.save(modulo)).thenReturn(moduloguardado);
        Modulo resultado = moduloService.crearModulo(modulo);
        assertThat(resultado.getId()).isEqualTo(1);
        verify(moduloRepository).save(modulo);

    }

    @Test
    void testListar(){
        Modulo m1 = new Modulo(1, "Ciclo While", 1, 1,
         null, true, null, null, null);
        Modulo m2 = new Modulo(2, "Ciclo For", 2, 1,
         null, true, null, null, null);
         when(moduloRepository.findAll()).thenReturn(Arrays.asList(m1,m2));
         List<Modulo> resultado = moduloService.listar();
         assertThat(resultado).hasSize(2).contains(m1,m2);
         verify(moduloRepository).findAll();
    }

    @Test
    void testEliminarModulo(){
        int idmodulo = 1;
        doNothing().when(moduloRepository).deleteById(idmodulo);
        moduloService.eliminarModulo(idmodulo);
        verify(moduloRepository).deleteById(idmodulo);
    }

    @Test
    void testBuscarxid(){
        int idexistente = 1;
        Modulo modulosimulado = new Modulo(idexistente, "Ciclo While", 1, 1,
         null, true, null, null, null);
        
        when(moduloRepository.findById(idexistente)).thenReturn(Optional.of(modulosimulado));
        Optional<Modulo> resultado = moduloService.buscarxid(idexistente);
        assertThat(resultado).isPresent().hasValueSatisfying(modulo -> {
            assertThat(modulo.getId()).isEqualTo(idexistente);
            assertThat(modulo.getTitulo()).isEqualTo("Ciclo While");
            assertThat(modulo.getDuracionsemanas()).isEqualTo(1);
            assertThat(modulo.getOrden()).isEqualTo(1);
            assertThat(modulo.getRecursos()).isEqualTo(null);
            assertThat(modulo.getEvaluaciones()).isEqualTo(null);
            assertThat(modulo.getContenido()).isEqualTo(null);
            assertThat(modulo.getCurso()).isEqualTo(null);
        });
        verify(moduloRepository).findById(idexistente);
    }

    @Test
    void testActualizarModulo(){
        int id = 1;
        Modulo existente = new Modulo(id, "Ciclo While", 1, 1,
         null, true, null, null, null);
        Modulo actualizado = new Modulo(id, "Fundamento de programacion", 3, 1,
         null, true, null, null, null);

        when(moduloRepository.findById(id)).thenReturn(Optional.of(existente));
        when(moduloRepository.save(existente)).thenReturn(existente);
        Optional<Modulo> resultado = moduloService.actualizarModulo(id, actualizado);
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getTitulo()).isEqualTo("Fundamento de programacion");
        assertThat(resultado.get().getDuracionsemanas()).isEqualTo(3);
        verify(moduloRepository).findById(id);
        verify(moduloRepository).save(existente);
    }
    
}

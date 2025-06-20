package com.Edutech.Modulo.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Modulo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int duracionsemanas;

    @Column(nullable = false)
    private int orden;

    @ElementCollection
    @CollectionTable(name = "modulo_recursos", joinColumns = @JoinColumn(name = "modulo_id"))
    @Column(name = "recursos")
    private List<String> recursos;

    @Column(nullable = false)
    private boolean esvisible;

    @Transient
    private List<EvaluacionDTO> evaluaciones = new ArrayList<>();

    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contenido> contenido = new ArrayList<>();

    @Transient
    private CursoDTO curso;
}

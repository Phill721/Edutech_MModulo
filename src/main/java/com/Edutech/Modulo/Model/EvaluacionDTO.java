package com.Edutech.Modulo.Model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluacionDTO {
    private int id;
    private String titulo;
    private LocalDate fecha;
    private double puntajetotal;

}

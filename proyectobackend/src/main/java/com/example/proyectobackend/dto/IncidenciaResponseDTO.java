package com.example.proyectobackend.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidenciaResponseDTO {

    private Long id;
    private String codigo;
    private String titulo;
    private String descripcion;
    private String areaSolicitante;
    private String prioridad;
    private String estado;
    private LocalDateTime fechaRegistro;
}


package com.example.proyectobackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class IncidenciaRequestDTO {

    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El área solicitante es obligatoria")
    private String areaSolicitante;

    @NotBlank(message = "La prioridad es obligatoria")
    @Pattern(regexp = "Alta|Media|Baja", message = "La prioridad debe ser Alta, Media o Baja")
    private String prioridad;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "Pendiente|En proceso|Resuelto", message = "El estado debe ser Pendiente, En proceso o Resuelto")
    private String estado;
}
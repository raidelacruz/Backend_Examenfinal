package com.example.proyectobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.proyectobackend.models.Incidencia;       

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia,Long>{
    List<Incidencia> findByTituloContainingIgnoreCase(String titulo);
    List<Incidencia> findByPrioridadAndEstado(String prioridad, String estado);

    long countByEstado(String estado);
}

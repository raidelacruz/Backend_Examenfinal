package com.example.proyectobackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyectobackend.models.Incidencia;
import com.example.proyectobackend.repository.IncidenciaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins = "*")
public class IncidenciaController {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @PostMapping
    public Incidencia RegistrarIncidencia(@RequestBody Incidencia nuevaIncidencia) {        
        return incidenciaRepository.save(nuevaIncidencia);
    }
    
    @GetMapping
    public List<Incidencia> listarIncidencias() {
        return incidenciaRepository.findAll();
    }

    @GetMapping("/buscar")
    public List<Incidencia> buscarPorTitulo(@RequestParam String titulo) {
        return incidenciaRepository.findByTituloContainingIgnoreCase(titulo);
    }


    @GetMapping("/filtrar")
    public List<Incidencia> filtrarPorPrioridadYEstado(
            @RequestParam String prioridad, 
            @RequestParam String estado) {
        return incidenciaRepository.findByPrioridadAndEstado(prioridad, estado);
    }

    @PutMapping("/{id}")
    public Incidencia actualizarIncidencia(@PathVariable Long id, @RequestBody Incidencia incidenciaActualizada) {
       
        Incidencia incidenciaExistente = incidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Incidencia no encontrada con el ID: " + id));

       
        incidenciaExistente.setTitulo(incidenciaActualizada.getTitulo());
        incidenciaExistente.setDescripcion(incidenciaActualizada.getDescripcion());
        incidenciaExistente.setAreaSolicitante(incidenciaActualizada.getAreaSolicitante());
        incidenciaExistente.setPrioridad(incidenciaActualizada.getPrioridad());
        incidenciaExistente.setEstado(incidenciaActualizada.getEstado());

        // Guardamos y devolvemos la incidencia actualizada
        return incidenciaRepository.save(incidenciaExistente);
    }

    @DeleteMapping("/{id}")
    public String eliminarIncidencia(@PathVariable Long id) {
        incidenciaRepository.deleteById(id);
        return "Incidencia eliminada correctamente";
    }
}

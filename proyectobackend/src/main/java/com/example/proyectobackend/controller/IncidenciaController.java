package com.example.proyectobackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyectobackend.dto.IncidenciaRequestDTO;
import com.example.proyectobackend.dto.IncidenciaResponseDTO;
import com.example.proyectobackend.models.Incidencia;
import com.example.proyectobackend.repository.IncidenciaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin(origins = "*")
public class IncidenciaController {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    // ---------- CREAR ----------
    @PostMapping
    public ResponseEntity<IncidenciaResponseDTO> registrarIncidencia(@Valid @RequestBody IncidenciaRequestDTO dto) {
        Incidencia nuevaIncidencia = new Incidencia();
        nuevaIncidencia.setCodigo(dto.getCodigo());
        nuevaIncidencia.setTitulo(dto.getTitulo());
        nuevaIncidencia.setDescripcion(dto.getDescripcion());
        nuevaIncidencia.setAreaSolicitante(dto.getAreaSolicitante());
        nuevaIncidencia.setPrioridad(dto.getPrioridad());
        nuevaIncidencia.setEstado(dto.getEstado());

        Incidencia guardada = incidenciaRepository.save(nuevaIncidencia);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapearADTO(guardada));
    }

    // ---------- LISTAR TODAS ----------
    @GetMapping
    public List<IncidenciaResponseDTO> listarIncidencias() {
        return incidenciaRepository.findAll()
                .stream()
                .map(this::mapearADTO)
                .toList();
    }

    // ---------- BUSCAR POR TÍTULO ----------
    @GetMapping("/buscar")
    public List<IncidenciaResponseDTO> buscarPorTitulo(@RequestParam String titulo) {
        return incidenciaRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::mapearADTO)
                .toList();
    }

    // ---------- FILTRAR POR PRIORIDAD Y ESTADO ----------
    @GetMapping("/filtrar")
    public List<IncidenciaResponseDTO> filtrarPorPrioridadYEstado(
            @RequestParam String prioridad,
            @RequestParam String estado) {
        return incidenciaRepository.findByPrioridadAndEstado(prioridad, estado)
                .stream()
                .map(this::mapearADTO)
                .toList();
    }

    // ---------- ACTUALIZAR ----------
    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDTO> actualizarIncidencia(
            @PathVariable Long id,
            @Valid @RequestBody IncidenciaRequestDTO dto) {

        Incidencia incidenciaExistente = incidenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Incidencia no encontrada con el ID: " + id));

        incidenciaExistente.setCodigo(dto.getCodigo());
        incidenciaExistente.setTitulo(dto.getTitulo());
        incidenciaExistente.setDescripcion(dto.getDescripcion());
        incidenciaExistente.setAreaSolicitante(dto.getAreaSolicitante());
        incidenciaExistente.setPrioridad(dto.getPrioridad());
        incidenciaExistente.setEstado(dto.getEstado());

        Incidencia actualizada = incidenciaRepository.save(incidenciaExistente);

        return ResponseEntity.ok(mapearADTO(actualizada));
    }

    // ---------- ELIMINAR ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarIncidencia(@PathVariable Long id) {
        if (!incidenciaRepository.existsById(id)) {
            throw new RuntimeException("Error: Incidencia no encontrada con el ID: " + id);
        }
        incidenciaRepository.deleteById(id);
        return ResponseEntity.ok("Incidencia eliminada correctamente");
    }

    // ---------- RESUMEN ----------
    @GetMapping("/resumen")
    public Map<String, Object> obtenerResumen() {
        Map<String, Object> resumen = new HashMap<>();

        resumen.put("total", incidenciaRepository.count());
        resumen.put("Pendiente", incidenciaRepository.countByEstado("Pendiente"));
        resumen.put("En proceso", incidenciaRepository.countByEstado("En proceso"));
        resumen.put("Resuelto", incidenciaRepository.countByEstado("Resuelto"));

        return resumen;
    }

    // ---------- MÉTODO PRIVADO DE CONVERSIÓN ----------
    private IncidenciaResponseDTO mapearADTO(Incidencia i) {
        return new IncidenciaResponseDTO(
                i.getId(), i.getCodigo(), i.getTitulo(), i.getDescripcion(),
                i.getAreaSolicitante(), i.getPrioridad(), i.getEstado(), i.getFechaRegistro()
        );
    }
}
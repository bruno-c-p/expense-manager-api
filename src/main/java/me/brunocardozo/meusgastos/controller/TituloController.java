package me.brunocardozo.meusgastos.controller;

import me.brunocardozo.meusgastos.domain.service.TituloService;
import me.brunocardozo.meusgastos.dto.titulo.TituloRequestDTO;
import me.brunocardozo.meusgastos.dto.titulo.TituloResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/titulos")
public class TituloController {
    @Autowired
    private TituloService service;
    @GetMapping
    public ResponseEntity<List<TituloResponseDTO>> obterTodos() {
        return ResponseEntity.ok(service.obterTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TituloResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterPorId(id));
    }
    @PostMapping
    public ResponseEntity<TituloResponseDTO> cadastrar(@RequestBody TituloRequestDTO dto) {
        return ResponseEntity.status(201).body(service.cadastrar(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TituloResponseDTO> atualizar(@PathVariable Long id, @RequestBody TituloRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

package me.brunocardozo.meusgastos.controller;

import me.brunocardozo.meusgastos.domain.service.CentroDeCustoService;
import me.brunocardozo.meusgastos.dto.centrodecusto.CentroDeCustoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/centrodecustos")
public class CentroDeCustoController {
    @Autowired
    private CentroDeCustoService service;
    @GetMapping
    public ResponseEntity<List<CentroDeCustoResponseDTO>> obterTodos() {
        return ResponseEntity.ok(service.obterTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CentroDeCustoResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obterPorId(id));
    }
    @PostMapping
    public ResponseEntity<CentroDeCustoResponseDTO> cadastrar(@RequestBody CentroDeCustoResponseDTO dto) {
        return ResponseEntity.status(201).body(service.cadastrar(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CentroDeCustoResponseDTO> atualizar(@PathVariable Long id, @RequestBody CentroDeCustoResponseDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

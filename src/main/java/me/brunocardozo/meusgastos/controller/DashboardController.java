package me.brunocardozo.meusgastos.controller;

import me.brunocardozo.meusgastos.domain.service.DashboardService;
import me.brunocardozo.meusgastos.dto.dashboard.DashboardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> obterFluxoDeCaixa(
            @RequestParam(name = "periodoInicial") String periodoInicial,
            @RequestParam(name = "periodoFinal") String periodoFinal
    ) {
        return ResponseEntity.ok(dashboardService.obterFluxoDeCaixa(periodoInicial, periodoFinal));
    }
}

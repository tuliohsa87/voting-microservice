package io.github.tuliohsa87.votingmicroservice.controller;

import io.github.tuliohsa87.votingmicroservice.dto.AgendaDTO;
import io.github.tuliohsa87.votingmicroservice.model.Agenda;
import io.github.tuliohsa87.votingmicroservice.service.AgendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/agenda")
public class AgendaController implements AgendaApi {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<AgendaDTO> createAgenda(@Valid @RequestBody AgendaDTO agendaDTO){
        return agendaService.createAgendaService(agendaDTO);
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> findAllAgenda(){
        return agendaService.findAllAgendaService();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> findById(@PathVariable(value = "id") UUID id){
        return agendaService.findByIdService(id);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Agenda> updateAgenda(@PathVariable(value = "id") UUID id, @Valid @RequestBody AgendaDTO agendaDTO){
        return agendaService.updateAgendaService(id, agendaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAgenda(@PathVariable(value = "id") UUID id){
        agendaService.deleteAgendaService(id);
    }

    @GetMapping("/close/{id}")
    public ResponseEntity<Agenda> closeAgenda(@PathVariable UUID id){
        return agendaService.closeAgendaService(id);
    }
}

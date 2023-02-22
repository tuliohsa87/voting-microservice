package io.github.tuliohsa87.votingmicroservice.controller;

import io.github.tuliohsa87.votingmicroservice.dto.AssociateAgendaDTO;
import io.github.tuliohsa87.votingmicroservice.service.AssociateAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/associate-agenda")
public class AssociateAgendaController implements AssociateAgendaApi{

    @Autowired
    private AssociateAgendaService associateAgendaService;

    @PostMapping
    public ResponseEntity<AssociateAgendaDTO> createAssociateAgenda(@RequestBody AssociateAgendaDTO associateAgendaDTO){
        return associateAgendaService.createAssociateAgendaService(associateAgendaDTO);
    }
}

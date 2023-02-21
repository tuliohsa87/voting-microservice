package io.github.tuliohsa87.votingmicroservice.controller;

import io.github.tuliohsa87.votingmicroservice.dto.SessionDTO;
import io.github.tuliohsa87.votingmicroservice.model.Session;
import io.github.tuliohsa87.votingmicroservice.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionDTO> createSession(@RequestBody SessionDTO sessionDTO){
        return sessionService.createSessionService(sessionDTO);
    }

    @GetMapping("/close/{id}")
    public ResponseEntity<Session> closeSession(@PathVariable Long id){
        return sessionService.closeSessionService(id);
    }

    @PostMapping("/update-time")
    public void extendVotingTime(@RequestBody SessionDTO sessionDTO){
        sessionService.updateVotingTimeService(sessionDTO);
    }

}

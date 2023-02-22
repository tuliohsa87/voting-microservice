package io.github.tuliohsa87.votingmicroservice.controller;

import io.github.tuliohsa87.votingmicroservice.dto.VoteDTO;
import io.github.tuliohsa87.votingmicroservice.service.SecretVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/vote")
public class SecretVoteController implements SecretVoteApi {

    @Autowired
    private SecretVoteService secretVoteService;

    @PostMapping
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteDTO){
        voteDTO.setTimestamp(LocalDateTime.now());
        return secretVoteService.createVoteService(voteDTO);
    }
}

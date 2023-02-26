package io.github.tuliohsa87.votingmicroservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VoteDTO {

    private String cpf;
    private String vote;
    private UUID agendaId;
    private LocalDateTime timestamp;
}

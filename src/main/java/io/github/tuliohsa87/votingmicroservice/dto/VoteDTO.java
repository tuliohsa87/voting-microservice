package io.github.tuliohsa87.votingmicroservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoteDTO {

    private String cpf;
    private String vote;
    private Long agendaId;
    private LocalDateTime timestamp;
}

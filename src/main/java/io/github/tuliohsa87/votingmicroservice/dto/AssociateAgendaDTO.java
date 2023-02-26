package io.github.tuliohsa87.votingmicroservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AssociateAgendaDTO {

    private String cpf;
    private UUID agendaId;

}

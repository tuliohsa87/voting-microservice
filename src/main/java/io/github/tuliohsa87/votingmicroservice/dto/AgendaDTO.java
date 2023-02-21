package io.github.tuliohsa87.votingmicroservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendaDTO {

    private Long id;
    private LocalDateTime timestamp;
    private String title;
    private String description;
    private String annotations;
}

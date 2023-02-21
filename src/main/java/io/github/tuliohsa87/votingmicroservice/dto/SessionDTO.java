package io.github.tuliohsa87.votingmicroservice.dto;

import io.github.tuliohsa87.votingmicroservice.enuns.SessionStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String topic;
    private LocalDateTime startedIn;
    private Long endSession;
    private SessionStatusEnum status;
    private Long agenda_id;
}

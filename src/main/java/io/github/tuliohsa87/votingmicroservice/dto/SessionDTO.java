package io.github.tuliohsa87.votingmicroservice.dto;

import io.github.tuliohsa87.votingmicroservice.enuns.SessionStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID agenda_id;
    @NotBlank(message = "The topic of the agenda cannot be blank.")
    @Size(min = 5, max = 255, message = "Enter between 5 to 255 characters for topic.")
    private String topic;
    private LocalDateTime startedIn;
    private Long endSession = 1L;
    private SessionStatusEnum status;
}

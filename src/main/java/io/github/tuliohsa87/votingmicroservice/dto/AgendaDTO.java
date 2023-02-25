package io.github.tuliohsa87.votingmicroservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendaDTO {

    private Long id;
    private LocalDateTime timestamp;
    @NotBlank(message = "Mandatory title.")
    @Size(min = 5, max = 255, message = "Enter between 5 to 255 characters for title.")
    private String title;
    @NotBlank(message = "Mandatory description.")
    @Size(min = 5, max = 1500, message = "Enter between 5 to 1500 characters for description.")
    private String description;
    private String annotations;
}

package io.github.tuliohsa87.votingmicroservice.controller;

import io.github.tuliohsa87.votingmicroservice.dto.AssociateAgendaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "associate-agenda", description = "The agenda and associate API")
public interface AssociateAgendaApi {

    @Operation(summary = "Create Association", description = "Make a bond of associate with the agenda.", tags = {"associate-agenda"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"cpf\": \"3\",\n" +
                            "    \"agendaId\": 102\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-21T23:36:23.273121100Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Associate agenda exception.\",\n" +
                            "    \"message\": \"The member is already in the group.\",\n" +
                            "    \"path\": \"/api/v1/associate-agenda\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-21T23:37:20.751234800Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Invalid agenda\",\n" +
                            "    \"message\": \"Please inform a valid agenda identifier!\",\n" +
                            "    \"path\": \"/api/v1/associate-agenda\"\n" +
                            "}"))
            })
    })
    @PostMapping
    public ResponseEntity<AssociateAgendaDTO> createAssociateAgenda(@RequestBody AssociateAgendaDTO associateAgendaDTO);
}

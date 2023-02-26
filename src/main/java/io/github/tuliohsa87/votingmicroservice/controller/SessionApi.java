package io.github.tuliohsa87.votingmicroservice.controller;

import io.github.tuliohsa87.votingmicroservice.dto.SessionDTO;
import io.github.tuliohsa87.votingmicroservice.model.Session;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "session", description = "The session API")
public interface SessionApi {

    @Operation(summary = "Create", description = "Create session API", tags = {"session"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"cpf\": \"1\",\n" +
                            "    \"agendaId\": 102\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-21T23:41:30.938169100Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Session already exists.\",\n" +
                            "    \"message\": \"There is already a registered session!\",\n" +
                            "    \"path\": \"/api/v1/session\"\n" +
                            "}"))
            })
    })
    @PostMapping
    ResponseEntity<SessionDTO> createSession(@RequestBody SessionDTO sessionDTO);

    @Operation(summary = "Close session", description = "For close session API", tags = {"session"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"id\": 102,\n" +
                            "    \"topic\": \"Topic session 002/2023\",\n" +
                            "    \"startedIn\": \"2023-02-21T20:46:11.746401\",\n" +
                            "    \"endsIn\": \"2023-02-21T21:35:29.159347\",\n" +
                            "    \"status\": \"CLOSED\",\n" +
                            "    \"votesYes\": 1,\n" +
                            "    \"votesNot\": 2,\n" +
                            "    \"agendaId\": 102,\n" +
                            "    \"agenda\": {\n" +
                            "        \"id\": 102,\n" +
                            "        \"timestamp\": \"2023-02-21T16:20:47.410339\",\n" +
                            "        \"title\": \"Pauta 005/2023\",\n" +
                            "        \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                            "        \"annotations\": null,\n" +
                            "        \"statusAgenda\": \"OPEN\"\n" +
                            "    },\n" +
                            "    \"howManyVoted\": 3,\n" +
                            "    \"howManyAbstained\": 2\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-22T00:07:04.493169300Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Invalid session.\",\n" +
                            "    \"message\": \"Please inform a valid identification key.\",\n" +
                            "    \"path\": \"/api/v1/session/close/2252\"\n" +
                            "}"))
            })
    })
    @GetMapping("/close/{id}")
    ResponseEntity<Session> closeSession(@PathVariable UUID id);

    @Operation(summary = "Update time", description = "For close session API", tags = {"session"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(hidden = true))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-21T23:49:03.238425800Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Invalid session.\",\n" +
                            "    \"message\": \"Please inform a valid identification key.\",\n" +
                            "    \"path\": \"/api/v1/session/update-time\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-21T23:48:12.847037400Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Session Exception.\",\n" +
                            "    \"message\": \"The session is already closed, so it will not be possible to update the time of the agenda.\",\n" +
                            "    \"path\": \"/api/v1/session/update-time\"\n" +
                            "}"))
            })
    })
    @PostMapping("/update-time")
    void extendVotingTime(@RequestBody SessionDTO sessionDTO);
}

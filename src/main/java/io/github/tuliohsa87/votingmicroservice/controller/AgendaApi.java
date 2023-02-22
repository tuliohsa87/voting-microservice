package io.github.tuliohsa87.votingmicroservice.controller;

import io.github.tuliohsa87.votingmicroservice.dto.AgendaDTO;
import io.github.tuliohsa87.votingmicroservice.model.Agenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "agenda", description = "The agenda API")
public interface AgendaApi {

    @Operation(summary = "Create agenda", description = "The agenda API", tags = {"agenda"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"id\": 52,\n" +
                            "    \"timestamp\": \"2023-02-21T13:14:00.8190121\",\n" +
                            "    \"title\": \"Pauta 004/2023\",\n" +
                            "    \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                            "    \"annotations\": null\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "504", description = "Gatway Timeout", content = {
                    @Content(mediaType = "application/json", schema = @Schema(hidden = true))
            })
    })
    @PostMapping
    ResponseEntity<AgendaDTO> createAgenda(@RequestBody AgendaDTO agendaDTO);

    @Operation(summary = "Find all agenda", description = "To list all the guidelines", tags = {"agenda"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "[\n" +
                            "    {\n" +
                            "        \"id\": 1,\n" +
                            "        \"timestamp\": \"2023-02-21T01:27:42.892941\",\n" +
                            "        \"title\": \"Pauta 002/2023\",\n" +
                            "        \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                            "        \"annotations\": \"Após a assembleia foram tomadas as seguintes decisões: Decisão A, Decisão B e Decisão C.\",\n" +
                            "        \"statusAgenda\": \"CLOSED\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"id\": 2,\n" +
                            "        \"timestamp\": \"2023-02-21T10:47:33.019256\",\n" +
                            "        \"title\": \"Pauta 002/2023\",\n" +
                            "        \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                            "        \"annotations\": \"Após a assembleia foram tomadas as seguintes decisões: Decisão A, Decisão B e Decisão C.\",\n" +
                            "        \"statusAgenda\": \"CLOSED\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"id\": 52,\n" +
                            "        \"timestamp\": \"2023-02-21T13:14:00.819012\",\n" +
                            "        \"title\": \"Pauta 004/2023\",\n" +
                            "        \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                            "        \"annotations\": null,\n" +
                            "        \"statusAgenda\": \"OPEN\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"id\": 102,\n" +
                            "        \"timestamp\": \"2023-02-21T16:20:47.410339\",\n" +
                            "        \"title\": \"Pauta 005/2023\",\n" +
                            "        \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                            "        \"annotations\": null,\n" +
                            "        \"statusAgenda\": \"OPEN\"\n" +
                            "    }\n" +
                            "]"))
            }),
            @ApiResponse(responseCode = "504", description = "Gatway Timeout", content = {
                    @Content(mediaType = "application/json", schema = @Schema(hidden = true))
            })
    })
    @GetMapping
    ResponseEntity<List<Agenda>> findAllAgenda();

    @Operation(summary = "Find agenda", description = "Search for ID", tags = {"agenda"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"id\": 52,\n" +
                    "    \"timestamp\": \"2023-02-21T13:14:00.819012\",\n" +
                    "    \"title\": \"Pauta 004/2023\",\n" +
                    "    \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                    "    \"annotations\": null,\n" +
                    "    \"statusAgenda\": \"OPEN\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"timestamp\": \"2023-02-21T21:38:33.586491900Z\",\n" +
                    "    \"status\": 400,\n" +
                    "    \"error\": \"Invalid agenda\",\n" +
                    "    \"message\": \"Please inform a valid agenda identifier!\",\n" +
                    "    \"path\": \"/api/v1/agenda/59\"\n" +
                    "}")))})
    @GetMapping("/{id}")
    ResponseEntity<Agenda> findById(@PathVariable(value = "id") Long id);

    @Operation(summary = "Update agenda", description = "Update", tags = {"agenda"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"id\": 52,\n" +
                            "    \"timestamp\": \"2023-02-21T13:14:00.819012\",\n" +
                            "    \"title\": \"Pauta 002/2023\",\n" +
                            "    \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                            "    \"annotations\": \"Após a assembleia foram tomadas as seguintes decisões: Decisão A, Decisão B e Decisão C.\",\n" +
                            "    \"statusAgenda\": \"OPEN\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "504", description = "Gatway Timeout", content = {
                    @Content(mediaType = "application/json", schema = @Schema(hidden = true))
            })
    })
    @PutMapping("/{id}")
    ResponseEntity<Agenda> updateAgenda(@PathVariable(value = "id") Long id, @RequestBody AgendaDTO agendaDTO);

    @Operation(summary = "Delete agenda", description = "Delete", tags = {"agenda"})
    @DeleteMapping("/{id}")
    void deleteAgenda(@PathVariable(value = "id") Long id);

    @Operation(summary = "Close agenda", description = "Close", tags = {"agenda"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"id\": 52,\n" +
                    "    \"timestamp\": \"2023-02-21T13:14:00.819012\",\n" +
                    "    \"title\": \"Pauta 002/2023\",\n" +
                    "    \"description\": \"Nesta pauta será discutido sobre melhorias nos seguintes Itens: Item A, Item B, Item C.\",\n" +
                    "    \"annotations\": \"Após a assembleia foram tomadas as seguintes decisões: Decisão A, Decisão B e Decisão C.\",\n" +
                    "    \"statusAgenda\": \"CLOSED\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"timestamp\": \"2023-02-21T21:31:41.290798500Z\",\n" +
                    "    \"status\": 400,\n" +
                    "    \"error\": \"Agenda Exception\",\n" +
                    "    \"message\": \"The agenda is already closed, it is not possible to close again.\",\n" +
                    "    \"path\": \"/api/v1/agenda/close/2\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"timestamp\": \"2023-02-21T21:36:02.966131100Z\",\n" +
                    "    \"status\": 400,\n" +
                    "    \"error\": \"Invalid agenda\",\n" +
                    "    \"message\": \"Please inform a valid agenda identifier!\",\n" +
                    "    \"path\": \"/api/v1/agenda/close/59\"\n" +
                    "}"))),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(schema = @Schema(example = "{\n" +
                    "    \"timestamp\": \"2023-02-21T21:35:16.886121600Z\",\n" +
                    "    \"status\": 400,\n" +
                    "    \"error\": \"Agenda Exception\",\n" +
                    "    \"message\": \"The agenda cannot be closed with blank notes.\",\n" +
                    "    \"path\": \"/api/v1/agenda/close/52\"\n" +
                    "}")))})
    @GetMapping("/close/{id}")
    ResponseEntity<Agenda> closeAgenda(@PathVariable Long id);
}

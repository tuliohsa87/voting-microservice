package io.github.tuliohsa87.votingmicroservice.controller;

import io.github.tuliohsa87.votingmicroservice.dto.VoteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "secret-vote", description = "The vote API")
public interface SecretVoteApi {

    @Operation(summary = "Vote", description = "The vote API", tags = {"secret-vote"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"cpf\": \"1\",\n" +
                            "    \"vote\": \"NOT\",\n" +
                            "    \"agendaId\": 102,\n" +
                            "    \"timestamp\": \"2023-02-21T21:30:39.5395372\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-22T00:31:41.261402600Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Invalid agenda\",\n" +
                            "    \"message\": \"Please inform a valid agenda identifier!\",\n" +
                            "    \"path\": \"/api/v1/vote\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "403", description = "unable to vote", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-22T04:05:43.899754200Z\",\n" +
                            "    \"status\": 403,\n" +
                            "    \"error\": \"Unable To Vote Exception \",\n" +
                            "    \"message\": \"The member has no right to vote.\",\n" +
                            "    \"path\": \"/api/v1/vote\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-22T00:32:31.292290Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Associate off the agenda.\",\n" +
                            "    \"message\": \"Associate does not belong to the group of this agenda.\",\n" +
                            "    \"path\": \"/api/v1/vote\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-22T00:33:06.656442Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"associate has already voted.\",\n" +
                            "    \"message\": \"The member cannot vote twice.\",\n" +
                            "    \"path\": \"/api/v1/vote\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-22T00:33:51.432485Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Invalid voting format.\",\n" +
                            "    \"message\": \"The voting format must be YES or NOT in upper case.\",\n" +
                            "    \"path\": \"/api/v1/vote\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-22T00:36:19.152588700Z\",\n" +
                            "    \"status\": 400,\n" +
                            "    \"error\": \"Expired voting time.\",\n" +
                            "    \"message\": \"Vote not computed by delay in shipping.\",\n" +
                            "    \"path\": \"/api/v1/vote\"\n" +
                            "}"))
            }),
            @ApiResponse(responseCode = "504", description = "Gatway Timeout", content = {
                    @Content(mediaType = "application/json", schema = @Schema(example = "{\n" +
                            "    \"timestamp\": \"2023-02-22T04:26:00.526289500Z\",\n" +
                            "    \"status\": 504,\n" +
                            "    \"error\": \"Gateway Timeout.\",\n" +
                            "    \"message\": \"Did not get a time response to consult CPF.\",\n" +
                            "    \"path\": \"/api/v1/vote\"\n" +
                            "}"))
            })
    })
    @PostMapping
    ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteDTO);
}

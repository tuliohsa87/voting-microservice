package io.github.tuliohsa87.votingmicroservice.service;

import io.github.tuliohsa87.votingmicroservice.dto.VoteDTO;
import io.github.tuliohsa87.votingmicroservice.enuns.SessionStatusEnum;
import io.github.tuliohsa87.votingmicroservice.enuns.VotesEnum;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.AssociateAgenda.AssociateHasAlreadyVoted;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.AssociateAgenda.AssociateOffTheAgenda;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.agenda.InvalidAgenda;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.InvalidSession;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.SessionException;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.vote.ExpiredVotingTime;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.vote.InvalidVotingFormat;
import io.github.tuliohsa87.votingmicroservice.model.Agenda;
import io.github.tuliohsa87.votingmicroservice.model.AssociateAgenda;
import io.github.tuliohsa87.votingmicroservice.model.SecretVote;
import io.github.tuliohsa87.votingmicroservice.model.Session;
import io.github.tuliohsa87.votingmicroservice.repository.AgendaRepository;
import io.github.tuliohsa87.votingmicroservice.repository.AssociateAgendaRepository;
import io.github.tuliohsa87.votingmicroservice.repository.SecretVoteRepository;
import io.github.tuliohsa87.votingmicroservice.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecretVoteService {

    @Autowired
    private AssociateAgendaRepository associateAgendaRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private SecretVoteRepository secretVoteRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public ResponseEntity<VoteDTO> createVoteService(VoteDTO voteDTO){

        Optional<Agenda> agenda = agendaRepository.findById(voteDTO.getAgendaId());
        if (agenda.isEmpty()) throw new InvalidAgenda("Please inform a valid agenda identifier!");

        Optional<AssociateAgenda> associateAgendaFound = associateAgendaRepository.findByAssociateAgendaForCpf(voteDTO.getCpf(), voteDTO.getAgendaId());
        if (associateAgendaFound.isEmpty()) throw new AssociateOffTheAgenda("Associate does not belong to the group of this agenda.");
        if (associateAgendaFound.get().getAlreadyVoted().equals(VotesEnum.YES)) throw new AssociateHasAlreadyVoted("The member cannot vote twice.");

        Optional<Session> session = sessionRepository.finByAgendaId(agenda.get().getId());
        if (session.isEmpty()) throw new InvalidSession("This agenda has no open session yet.");
        if (session.get().getStatus().equals(SessionStatusEnum.CLOSED)) throw new SessionException("The session is already closed, so it will not be possible to vote.");

        boolean isAfter = voteDTO.getTimestamp().isAfter(session.get().getEndsIn());
        if (isAfter) throw new ExpiredVotingTime("Vote not computed by delay in shipping.");

        try {
            VotesEnum vote = VotesEnum.valueOf(voteDTO.getVote().toUpperCase());
            SecretVote secretVote = new SecretVote();
            secretVote.setTimestamp(voteDTO.getTimestamp());
            secretVote.setVotesEnum(vote);
            secretVote.setSessionSecretVote(session.get());
            SecretVote secretCreate = secretVoteRepository.save(secretVote);

            AssociateAgenda associateAgenda = associateAgendaFound.get();
            associateAgenda.setAlreadyVoted(VotesEnum.YES);
            associateAgendaRepository.save(associateAgenda);
            return new ResponseEntity<>(voteDTO, HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            throw new InvalidVotingFormat("The voting format must be YES or NOT in upper case.");
        }
    }
}

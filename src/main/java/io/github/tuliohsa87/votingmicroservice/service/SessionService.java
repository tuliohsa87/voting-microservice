package io.github.tuliohsa87.votingmicroservice.service;

import io.github.tuliohsa87.votingmicroservice.dto.SessionDTO;
import io.github.tuliohsa87.votingmicroservice.enuns.SessionStatusEnum;
import io.github.tuliohsa87.votingmicroservice.enuns.VotesEnum;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.agenda.InvalidAgenda;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.InvalidSession;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.SessionAlreadyExists;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.SessionException;
import io.github.tuliohsa87.votingmicroservice.model.Agenda;
import io.github.tuliohsa87.votingmicroservice.model.AssociateAgenda;
import io.github.tuliohsa87.votingmicroservice.model.SecretVote;
import io.github.tuliohsa87.votingmicroservice.model.Session;
import io.github.tuliohsa87.votingmicroservice.repository.AgendaRepository;
import io.github.tuliohsa87.votingmicroservice.repository.AssociateAgendaRepository;
import io.github.tuliohsa87.votingmicroservice.repository.SecretVoteRepository;
import io.github.tuliohsa87.votingmicroservice.repository.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private SecretVoteService secretVoteService;

    @Autowired
    SecretVoteRepository secretVoteRepository;

    @Autowired
    AssociateAgendaRepository associateAgendaRepository;

    public ResponseEntity<SessionDTO> createSessionService(SessionDTO sessionDTO){
        Long id = sessionDTO.getAgenda_id();
        if (id == null) throw new InvalidAgenda("The key to the agenda is mandatory.");
        Optional<Agenda> agendaFound = agendaRepository.findById(id);
        if (agendaFound.isEmpty()) throw new InvalidAgenda("Please inform a valid agenda identifier!");
        Optional<Session> sessionTest = Optional.ofNullable(agendaFound.get().getSession());
        if (sessionTest.isPresent()) throw new SessionAlreadyExists("There is already a registered session!");

        Session sessionNew = new Session();
        sessionDTO.setStartedIn(LocalDateTime.now());
        sessionNew.setEndsIn(LocalDateTime.now().plusMinutes(sessionDTO.getEndSession()));
        BeanUtils.copyProperties(sessionDTO, sessionNew);
        sessionNew.setStatus(SessionStatusEnum.OPEN);
        sessionNew.setAgendaId(sessionDTO.getAgenda_id());
        sessionNew.setAgenda(agendaFound.get());
        try {
            Session session = sessionRepository.save(sessionNew);
            BeanUtils.copyProperties(session, sessionDTO);
            return new ResponseEntity<>(sessionDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    public ResponseEntity<Session> closeSessionService(Long id){
        Optional<Session> sessionFound = sessionRepository.findById(id);
        if (sessionFound.isEmpty()) throw new InvalidSession("Please inform a valid identification key.");
        Session session = sessionFound.get();
        if (session.getStatus().equals(SessionStatusEnum.CLOSED)) throw new SessionException("The session is already closed, it is not possible to close again.");

        Optional<List<SecretVote>> secretVotes = secretVoteRepository.getSecreteVotes(id);
        List<SecretVote> votes = secretVotes.get();
        Long votesNot = votes.stream()
                .filter(e -> e.getVotesEnum().equals(VotesEnum.NOT))
                .count();
        session.setVotesNot(votesNot);
        Long votesYes = votes.stream()
                .filter(e -> e.getVotesEnum().equals(VotesEnum.YES))
                .count();
        session.setVotesYes(votesYes);

        Optional<List<AssociateAgenda>> associateAgendaFound = associateAgendaRepository.findByAssociateAgendaForAgenda(sessionFound.get().getAgendaId());
        List<AssociateAgenda> associateAgenda = associateAgendaFound.get();
        Long howManyAbstained = associateAgenda.stream()
                .filter(e -> e.getAlreadyVoted().equals(VotesEnum.NOT))
                .count();
        session.setHowManyAbstained(howManyAbstained);
        Long howManyVoted = associateAgenda.stream()
                .filter(e -> e.getAlreadyVoted().equals(VotesEnum.YES))
                .count();
        session.setHowManyVoted(howManyVoted);

        try {
            session.setStatus(SessionStatusEnum.CLOSED);
            Session sessionUpdated = sessionRepository.save(session);
            return new ResponseEntity<>(sessionUpdated, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public void updateVotingTimeService(SessionDTO sessionDTO){
        Optional<Session> sessionFound = sessionRepository.finByAgendaId(sessionDTO.getAgenda_id());
        if (sessionFound.isEmpty()) throw new InvalidSession("Please inform a valid identification key.");
        if (sessionFound.get().getStatus().equals(SessionStatusEnum.CLOSED)) throw new SessionException("The session is already closed, so it will not be possible to update the time of the agenda.");
        Session session = sessionFound.get();
        LocalDateTime timeNow = LocalDateTime.now().plusMinutes(sessionDTO.getEndSession());
        session.setEndsIn(timeNow);
        try {
            Session sessionUpdated = sessionRepository.save(session);
        }catch (Exception e){}
    }
}

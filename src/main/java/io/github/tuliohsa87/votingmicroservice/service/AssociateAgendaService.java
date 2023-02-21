package io.github.tuliohsa87.votingmicroservice.service;

import io.github.tuliohsa87.votingmicroservice.dto.AssociateAgendaDTO;
import io.github.tuliohsa87.votingmicroservice.enuns.SessionStatusEnum;
import io.github.tuliohsa87.votingmicroservice.enuns.VotesEnum;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.AssociateAgenda.AssociateAgendaException;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.agenda.InvalidAgenda;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.SessionException;
import io.github.tuliohsa87.votingmicroservice.model.Agenda;
import io.github.tuliohsa87.votingmicroservice.model.AssociateAgenda;
import io.github.tuliohsa87.votingmicroservice.model.Session;
import io.github.tuliohsa87.votingmicroservice.repository.AgendaRepository;
import io.github.tuliohsa87.votingmicroservice.repository.AssociateAgendaRepository;
import io.github.tuliohsa87.votingmicroservice.repository.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AssociateAgendaService {

    @Autowired
    private AssociateAgendaRepository associateAgendaRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public ResponseEntity<AssociateAgendaDTO> createAssociateAgendaService(AssociateAgendaDTO associateAgendaDTO){
        Optional<Agenda> agendaFound = agendaRepository.findById(associateAgendaDTO.getAgendaId());
        if (agendaFound.isEmpty()) throw new InvalidAgenda("Please inform a valid agenda identifier!");

        Optional<Session> session = sessionRepository.finByAgendaId(agendaFound.get().getId());
        if (session.isPresent()){
            if (agendaFound.get().getSession().getStatus().equals(SessionStatusEnum.CLOSED)) throw new SessionException("The session is already closed, it is not possible to insert new members.");
            boolean isAfter = LocalDateTime.now().isAfter(agendaFound.get().getSession().getEndsIn());
            if (isAfter) throw new AssociateAgendaException("The time of the session has expired, so it will not be possible to add new associates to the group of this agenda.");
        }

        Optional<AssociateAgenda> associateAgendaFound = associateAgendaRepository.findByAssociateAgendaForCpf(associateAgendaDTO.getCpf(),associateAgendaDTO.getAgendaId());
        if (associateAgendaFound.isPresent()) throw new AssociateAgendaException("The member is already in the group.");

        try {
            AssociateAgenda associateAgenda = new AssociateAgenda();
            BeanUtils.copyProperties(associateAgendaDTO, associateAgenda);
            associateAgenda.setAlreadyVoted(VotesEnum.NOT);
            associateAgenda.setAgendaId(associateAgendaDTO.getAgendaId());
            associateAgenda.setAgenda(agendaFound.get());
            associateAgendaRepository.save(associateAgenda);
            return new ResponseEntity<>(associateAgendaDTO, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }
}

package io.github.tuliohsa87.votingmicroservice.service;

import io.github.tuliohsa87.votingmicroservice.dto.AgendaDTO;
import io.github.tuliohsa87.votingmicroservice.enuns.StatusOfTheAgendaEnum;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.agenda.AgendaException;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.agenda.InvalidAgenda;
import io.github.tuliohsa87.votingmicroservice.model.Agenda;
import io.github.tuliohsa87.votingmicroservice.repository.AgendaRepository;
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
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    public ResponseEntity<AgendaDTO> createAgendaService(AgendaDTO agendaDTO) {
        Agenda agendaModelNew = new Agenda();
        BeanUtils.copyProperties(agendaDTO, agendaModelNew);
        agendaModelNew.setTimestamp(LocalDateTime.now());
        agendaModelNew.setStatusAgenda(StatusOfTheAgendaEnum.OPEN);
        try {
            Agenda agenda = agendaRepository.save(agendaModelNew);
            BeanUtils.copyProperties(agenda, agendaDTO);
            return new  ResponseEntity<>(agendaDTO, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    public ResponseEntity<List<Agenda>> findAllAgendaService(){
        try {
            return new ResponseEntity<>(agendaRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    public ResponseEntity<Agenda> findByIdService(Long id) {
        Optional<Agenda> agendaFound = agendaRepository.findById(id);
        if (agendaFound.isEmpty()) throw new InvalidAgenda("Please inform a valid agenda identifier!");
        try {
            return new ResponseEntity<Agenda>(agendaFound.get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    public ResponseEntity<Agenda> updateAgendaService(Long id, AgendaDTO agendaDTO){
        try {
            Agenda agenda = agendaRepository.findById(id).get();
            agenda.setTitle(agendaDTO.getTitle());
            agenda.setDescription(agendaDTO.getDescription());
            agenda.setAnnotations(agendaDTO.getAnnotations());
            Agenda updatedAgenda = agendaRepository.save(agenda);
            return new ResponseEntity<Agenda>(updatedAgenda, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    public void deleteAgendaService(Long id){
        try {
            Agenda agenda = agendaRepository.findById(id).get();
            agendaRepository.deleteById(agenda.getId());
        }catch (Exception e){
            new ResponseEntity<>(HttpStatus.GATEWAY_TIMEOUT);
        }
    }

    public ResponseEntity<Agenda> closeAgendaService(Long id){
        Optional<Agenda> agendaFound = agendaRepository.findById(id);
        if (agendaFound.isEmpty()) throw new InvalidAgenda("Please inform a valid agenda identifier!");
        Agenda agenda = agendaFound.get();
        if (agenda.getStatusAgenda().equals(StatusOfTheAgendaEnum.CLOSED)) throw new AgendaException("The agenda is already closed, it is not possible to close again.");

        Optional<String> annotations = Optional.ofNullable(agendaFound.get().getAnnotations());
        if (annotations.isEmpty()) throw new AgendaException("The agenda cannot be closed with blank notes.");

        try {
            sessionService.closeSessionService(agenda.getSession().getId());
            agenda.setStatusAgenda(StatusOfTheAgendaEnum.CLOSED);
            agendaRepository.save(agenda);
            return new ResponseEntity<>(agenda, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }
}

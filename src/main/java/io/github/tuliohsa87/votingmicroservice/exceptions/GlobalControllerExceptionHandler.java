package io.github.tuliohsa87.votingmicroservice.exceptions;

import io.github.tuliohsa87.votingmicroservice.exceptions.exception.AssociateAgenda.AssociateAgendaException;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.AssociateAgenda.AssociateHasAlreadyVoted;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.AssociateAgenda.AssociateOffTheAgenda;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.agenda.AgendaException;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.agenda.InvalidAgenda;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.server.GatewayTimeoutException;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.InvalidSession;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.SessionAlreadyExists;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.session.SessionException;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.vote.ExpiredVotingTime;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.vote.InvalidVotingFormat;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.vote.UnableToVoteException;
import io.github.tuliohsa87.votingmicroservice.exceptions.exception.vote.VoteException;
import io.github.tuliohsa87.votingmicroservice.exceptions.model.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(AgendaException.class)
    public ResponseEntity<StandardError> emailNotSent(AgendaException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Agenda Exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InvalidAgenda.class)
    public ResponseEntity<StandardError> emailNotSent(InvalidAgenda e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Invalid agenda");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SessionException.class)
    public ResponseEntity<StandardError> emailNotSent(SessionException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Session Exception.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InvalidSession.class)
    public ResponseEntity<StandardError> emailNotSent(InvalidSession e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Invalid session.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SessionAlreadyExists.class)
    public ResponseEntity<StandardError> emailNotSent(SessionAlreadyExists e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Session already exists.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(VoteException.class)
    public ResponseEntity<StandardError> emailNotSent(VoteException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Exceção de voto.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ExpiredVotingTime.class)
    public ResponseEntity<StandardError> emailNotSent(ExpiredVotingTime e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Expired voting time.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InvalidVotingFormat.class)
    public ResponseEntity<StandardError> emailNotSent(InvalidVotingFormat e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Invalid voting format.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AssociateAgendaException.class)
    public ResponseEntity<StandardError> emailNotSent(AssociateAgendaException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Associate agenda exception.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AssociateOffTheAgenda.class)
    public ResponseEntity<StandardError> emailNotSent(AssociateOffTheAgenda e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Associate off the agenda.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AssociateHasAlreadyVoted.class)
    public ResponseEntity<StandardError> emailNotSent(AssociateHasAlreadyVoted e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("associate has already voted.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }
    @ExceptionHandler(UnableToVoteException.class)
    public ResponseEntity<StandardError> emailNotSent(UnableToVoteException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(FORBIDDEN.value());
        err.setError("Associate Has Already Voted.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(FORBIDDEN).body(err);
    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity<StandardError> emailNotSent(GatewayTimeoutException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(GATEWAY_TIMEOUT.value());
        err.setError("Gateway Timeout.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(GATEWAY_TIMEOUT).body(err);
    }
}

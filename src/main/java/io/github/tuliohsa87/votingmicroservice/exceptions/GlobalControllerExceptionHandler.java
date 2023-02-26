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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(AgendaException.class)
    public ResponseEntity<StandardError> exceptionCustomized(AgendaException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Agenda Exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InvalidAgenda.class)
    public ResponseEntity<StandardError> exceptionCustomized(InvalidAgenda e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Invalid agenda");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SessionException.class)
    public ResponseEntity<StandardError> exceptionCustomized(SessionException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Session Exception.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InvalidSession.class)
    public ResponseEntity<StandardError> exceptionCustomized(InvalidSession e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Invalid session.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(SessionAlreadyExists.class)
    public ResponseEntity<StandardError> exceptionCustomized(SessionAlreadyExists e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Session already exists.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(VoteException.class)
    public ResponseEntity<StandardError> exceptionCustomized(VoteException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Exceção de voto.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ExpiredVotingTime.class)
    public ResponseEntity<StandardError> exceptionCustomized(ExpiredVotingTime e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Expired voting time.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InvalidVotingFormat.class)
    public ResponseEntity<StandardError> exceptionCustomized(InvalidVotingFormat e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Invalid voting format.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AssociateAgendaException.class)
    public ResponseEntity<StandardError> exceptionCustomized(AssociateAgendaException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Associate agenda exception.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AssociateOffTheAgenda.class)
    public ResponseEntity<StandardError> exceptionCustomized(AssociateOffTheAgenda e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("Associate off the agenda.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AssociateHasAlreadyVoted.class)
    public ResponseEntity<StandardError> exceptionCustomized(AssociateHasAlreadyVoted e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(BAD_REQUEST.value());
        err.setError("associate has already voted.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }
    @ExceptionHandler(UnableToVoteException.class)
    public ResponseEntity<StandardError> exceptionCustomized(UnableToVoteException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(FORBIDDEN.value());
        err.setError("Associate Has Already Voted.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(FORBIDDEN).body(err);
    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity<StandardError> exceptionCustomized(GatewayTimeoutException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(GATEWAY_TIMEOUT.value());
        err.setError("Gateway Timeout.");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(GATEWAY_TIMEOUT).body(err);
    }
}

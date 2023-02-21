package io.github.tuliohsa87.votingmicroservice.exceptions.exception.session;

public class SessionAlreadyExists extends RuntimeException {

    public SessionAlreadyExists(String message){
        super(message);
    }
}

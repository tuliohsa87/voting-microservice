package io.github.tuliohsa87.votingmicroservice.exceptions.exception.session;

public class InvalidSession extends RuntimeException {

    public InvalidSession(String message){
        super(message);
    }
}

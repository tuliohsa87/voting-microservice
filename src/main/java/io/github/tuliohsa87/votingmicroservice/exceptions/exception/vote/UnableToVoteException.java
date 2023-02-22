package io.github.tuliohsa87.votingmicroservice.exceptions.exception.vote;

public class UnableToVoteException extends RuntimeException{

    public UnableToVoteException(String message){
        super(message);
    }
}

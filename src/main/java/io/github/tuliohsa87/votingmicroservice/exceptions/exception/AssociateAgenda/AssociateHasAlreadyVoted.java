package io.github.tuliohsa87.votingmicroservice.exceptions.exception.AssociateAgenda;

public class AssociateHasAlreadyVoted extends RuntimeException{

    public AssociateHasAlreadyVoted(String message){
        super(message);
    }
}

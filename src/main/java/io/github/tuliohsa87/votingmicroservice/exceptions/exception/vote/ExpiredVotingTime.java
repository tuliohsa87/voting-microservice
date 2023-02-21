package io.github.tuliohsa87.votingmicroservice.exceptions.exception.vote;

public class ExpiredVotingTime extends RuntimeException {

    public ExpiredVotingTime(String message){
        super(message);
    }
}

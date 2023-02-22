package io.github.tuliohsa87.votingmicroservice.exceptions.exception.server;

public class GatewayTimeoutException extends RuntimeException{

    public GatewayTimeoutException(String message){
        super(message);
    }
}

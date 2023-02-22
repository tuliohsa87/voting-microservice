package io.github.tuliohsa87.votingmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VotingMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingMicroserviceApplication.class, args);
	}

}

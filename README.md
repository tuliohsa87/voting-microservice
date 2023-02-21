# Example of microservice to vote on the agenda

The Project Consists in a microservice to facilitate the creation of guidelines and voting session, in which members authorized to participate in the vote have a standard time of 1 minute to send their vote that may be yes or no, but this time may be higher if defined at the time of the creation of the session or even expanded if necessary.

## Requirements

For building and running the application you need:

* Java JDK 17
* Maven 2

## Setup

For project local execution you need to access the `application.properties` file and modify the Prod environment variable for Dev as per below.

Production environment

        spring.profiles.active=prod

Development environment

        spring.profiles.active=dev

## Running the application locally

The simplest way to put the project to work is to perform the `` main`` method in the `io.github.tuliohsa87.votingmicroservice.VotingMicroserviceApplication` class from your IDE, whether STS or INTELLIJ.

Another way is through the Maven command line:

``mvn spring-boot:run``

## Versioning

This project is using semantic version practices 2.0.0

For more information about semantic version click [here](https://semver.org/lang/pt-BR/)

## Copyright

Released under the MIT. See the [LICENSE](LICENSE) file.

See more about licensing a repository click [here](https://docs.github.com/pt/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/licensing-a-repository)

To choose an ideal license consult [choosealicense.com](https://choosealicense.com/)
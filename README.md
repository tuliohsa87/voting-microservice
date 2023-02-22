# Example of microservice to vote on the agenda

The Project Consists in a microservice to facilitate the creation of guidelines and voting session, in which members authorized to participate in the vote have a standard time of 1 minute to send their vote that may be yes or no, but this time may be higher if defined at the time of the creation of the session or even expanded if necessary.

## Requirements

For building and running the application you need:

* Java JDK 17
* Maven 2

## Dependencies

Além das dependências que esse microsserviço possui em seu arquivo de configuração `pom.xml`. Ele realiza consultas em uma API externa chamada [user-info](https://github.com/tuliohsa87/user-info), utilizando como meio de integração a biblioteca do [Spring Cloud OpenFeign](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/). Então se você pretende testar localmente será necessário baixar ela do GitHub e testar as duas juntas.

In addition to the dependencies this microservice has in your `pom.xml` configuration file. He conducts consultations on an external API called [user-info](https://github.com/tuliohsa87/user-info), using as a means of integrating the [Spring Cloud OpenFeign](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/). So if you intend to test locally you will need to download it from Github and test both together.

* [user-info](https://github.com/tuliohsa87/user-info)

## Setup

For project local execution you need to access the `application.yml` file and modify the Prod environment variable for Dev as per below.

Production environment

        #environment dev/prod
        spring:
          profiles:
            active: prod

Development environment

        #environment dev/prod
        spring:
          profiles:
            active: dev

## Running the application locally

The simplest way to put the project to work is to perform the `` main`` method in the `io.github.tuliohsa87.votingmicroservice.VotingMicroserviceApplication` class from your IDE, whether STS or INTELLIJ.

Another way is through the Maven command line:

``mvn spring-boot:run``

## Documentation

* Documentation will be available in HTML format, using the official [swagger-ui jars](https://github.com/swagger-api/swagger-ui) 

* The Swagger UI page will then be available at http\://server:port/context-path/swagger-ui.html and the OpenAPI description will be available at the following url for json format: http\://server:port/context-path/v3/api-docs

  * server: The server name or IP
  * port: The server port
  * context-path: The context path of the application

* Documentation can be available in yaml format as well, on the following path : /v3/api-docs.yaml

Example: `http://localhost:8087/api/swagger-ui/index.html`

## Versioning

This project is using semantic version practices 2.0.0

In addition to using semantic version control, the version has also been inserted into each controller's URL, so when new features are created, they do not affect users who depend on the previous version and there will be a necessary time until users suit the new functionalities.

For more information about semantic version click [here](https://semver.org/lang/pt-BR/)

## Copyright

Released under the MIT. See the [LICENSE](LICENSE) file.

See more about licensing a repository click [here](https://docs.github.com/pt/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/licensing-a-repository)

To choose an ideal license consult [choosealicense.com](https://choosealicense.com/)
package io.github.tuliohsa87.votingmicroservice.repository;

import io.github.tuliohsa87.votingmicroservice.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, UUID> {
}

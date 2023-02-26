package io.github.tuliohsa87.votingmicroservice.repository;

import io.github.tuliohsa87.votingmicroservice.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Query(value = "select * from session s where s.agenda_id = :id", nativeQuery = true)
    Optional<Session> finByAgendaId(UUID id);
}

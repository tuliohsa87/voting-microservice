package io.github.tuliohsa87.votingmicroservice.repository;

import io.github.tuliohsa87.votingmicroservice.model.AssociateAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssociateAgendaRepository extends JpaRepository<AssociateAgenda, UUID> {

    @Query(value = "select * from associate_agenda a where a.cpf = :cpf and a.agenda_id = :agendaId", nativeQuery = true)
    Optional<AssociateAgenda> findByAssociateAgendaForCpf(String cpf, UUID agendaId);

    @Query(value = "select * from associate_agenda a where a.agenda_id = :agendaId", nativeQuery = true)
    Optional<List<AssociateAgenda>> findByAssociateAgendaForAgenda(UUID agendaId);
}

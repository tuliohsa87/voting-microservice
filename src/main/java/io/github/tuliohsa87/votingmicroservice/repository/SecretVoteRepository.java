package io.github.tuliohsa87.votingmicroservice.repository;

import io.github.tuliohsa87.votingmicroservice.model.SecretVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SecretVoteRepository extends JpaRepository<SecretVote, UUID> {

    @Query(value = "select * from secret_vote sv where sv.session_id = :id", nativeQuery = true)
    Optional<List<SecretVote>> getSecreteVotes(UUID id);
}

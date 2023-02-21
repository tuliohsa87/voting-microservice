package io.github.tuliohsa87.votingmicroservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.tuliohsa87.votingmicroservice.enuns.VotesEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class SecretVote implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "vote")
    private VotesEnum votesEnum;

    private LocalDateTime timestamp;

    @Column(insertable=false, updatable=false)
    @Transient
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session sessionSecretVote;

    @JsonManagedReference
    public Session getSession() {
        return sessionSecretVote;
    }


}

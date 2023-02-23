package io.github.tuliohsa87.votingmicroservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.tuliohsa87.votingmicroservice.enuns.SessionStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String topic;
    private LocalDateTime startedIn;
    private LocalDateTime endsIn;
    private SessionStatusEnum status;
    private Long votesYes;
    private Long votesNot;
    private Long HowManyVoted;
    private Long HowManyAbstained;
    @Column(name = "agenda_id",insertable=false, updatable=false)
    private Long agendaId;
    @OneToOne
    private Agenda agenda;

    @OneToMany(mappedBy = "sessionSecretVote", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SecretVote> secretVotes;

    @JsonBackReference
    public Agenda getAgenda() {
        return agenda;
    }

    @JsonBackReference
    public List<SecretVote> getSecretVotes() {
        return secretVotes;
    }
}

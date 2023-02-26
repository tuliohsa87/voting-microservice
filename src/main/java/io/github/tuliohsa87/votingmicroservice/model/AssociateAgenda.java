package io.github.tuliohsa87.votingmicroservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.tuliohsa87.votingmicroservice.enuns.VotesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssociateAgenda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private VotesEnum alreadyVoted;
    private String cpf;
    @Column(name = "agenda_id",insertable=false, updatable=false)
    private UUID agendaId;
    @ManyToOne
    private Agenda agenda;

    @JsonManagedReference
    public Agenda getAgenda() {
        return agenda;
    }
}

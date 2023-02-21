package io.github.tuliohsa87.votingmicroservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.tuliohsa87.votingmicroservice.enuns.StatusOfTheAgendaEnum;
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
@NoArgsConstructor
@AllArgsConstructor
public class Agenda implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime timestamp;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String annotations;

    private StatusOfTheAgendaEnum statusAgenda;

    @OneToMany(mappedBy = "agenda", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AssociateAgenda> associateAgenda;

    @OneToOne(mappedBy = "agenda", orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "agenda_id")
    private Session session;

    @JsonBackReference
    public Session getSession() {
        return session;
    }

    @JsonBackReference
    public List<AssociateAgenda> getAssociateAgenda() {
        return associateAgenda;
    }
}

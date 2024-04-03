package dev.omedia.boot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class JuridicalPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COMMENT")
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "JURIDICAL_PERSON_ID", referencedColumnName = "id")
    private Collection<Contactor> contactors;
}

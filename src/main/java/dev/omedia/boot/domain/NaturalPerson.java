package dev.omedia.boot.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@ToString
public class NaturalPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "BIRTH_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "NATURAL_PERSON_ID", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_CONTACT_NATURAL_PERSON_ID"))
    private Collection<Contact> contacts;
}

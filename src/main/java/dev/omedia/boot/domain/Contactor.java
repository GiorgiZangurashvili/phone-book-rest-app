package dev.omedia.boot.domain;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Contactor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "BIRTH_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contactor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_CONTACT_CONTACTOR_ID"))
    private Collection<Contact> contacts;
}

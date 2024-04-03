package dev.omedia.boot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "LABEL", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Label label;

    @Column(name = "VALUE", nullable = false)
    private String value;
}

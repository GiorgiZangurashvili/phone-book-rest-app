package dev.omedia.boot.repository;

import dev.omedia.boot.domain.JuridicalPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuridicalPersonRepository extends CrudRepository<JuridicalPerson, Long> {
}

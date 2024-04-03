package dev.omedia.boot.repository;

import dev.omedia.boot.domain.NaturalPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NaturalPersonRepository extends CrudRepository<NaturalPerson, Long> {
}

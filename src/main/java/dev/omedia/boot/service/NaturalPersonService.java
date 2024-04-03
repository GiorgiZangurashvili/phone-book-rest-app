package dev.omedia.boot.service;

import dev.omedia.boot.domain.Contact;
import dev.omedia.boot.domain.NaturalPerson;
import dev.omedia.boot.dto.NaturalPersonDTO;
import dev.omedia.boot.exception.EntityNotFoundException;
import dev.omedia.boot.mapper.NaturalPersonMapper;
import dev.omedia.boot.repository.NaturalPersonRepository;
import dev.omedia.boot.validation.NaturalPersonValidation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NaturalPersonService {
    private final NaturalPersonRepository repository;

    public NaturalPersonService(NaturalPersonRepository repository) {
        this.repository = repository;
    }

    public Optional<NaturalPersonDTO> findById(long id) {
        NaturalPersonDTO dto = NaturalPersonMapper.entityToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Natural person with id=" + id + " does not exist")));
        return Optional.of(dto);
    }

    public Collection<NaturalPersonDTO> findALl() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(NaturalPersonMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public NaturalPersonDTO save(NaturalPersonDTO dto) {
        NaturalPersonValidation.validateNaturalPerson(dto);
        NaturalPerson entity = NaturalPersonMapper.dtoToEntity(dto);
        NaturalPerson savedPerson = repository.save(entity);
        return NaturalPersonMapper.entityToDTO(savedPerson);
    }

    public NaturalPersonDTO update(long id, NaturalPersonDTO dto) {
        NaturalPerson person = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Natural person with id=" + id + " not found"));
        NaturalPerson entity = NaturalPersonMapper.dtoToEntity(dto);
        entity.setId(id);
        Collection<Contact> newContacts = new ArrayList<>();
        for (Contact contact : person.getContacts()) {
            if (!entity.getContacts().contains(contact)) {
                newContacts.add(contact);
            }
        }
        newContacts.addAll(entity.getContacts());
        entity.setContacts(newContacts);
        NaturalPerson savedPerson = repository.save(entity);
        return NaturalPersonMapper.entityToDTO(savedPerson);
    }

    public void delete(long id) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("Natural person with id=%d was not found", id)));
        repository.deleteById(id);
    }
}

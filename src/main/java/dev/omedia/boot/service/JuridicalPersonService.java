package dev.omedia.boot.service;

import dev.omedia.boot.domain.*;
import dev.omedia.boot.dto.JuridicalPersonDTO;
import dev.omedia.boot.exception.EntityNotFoundException;
import dev.omedia.boot.exception.MergeException;
import dev.omedia.boot.mapper.JuridicalPersonMapper;
import dev.omedia.boot.repository.JuridicalPersonRepository;
import dev.omedia.boot.repository.NaturalPersonRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JuridicalPersonService {
    private final JuridicalPersonRepository repository;
    private final NaturalPersonRepository naturalPersonRepository;

    public JuridicalPersonService(JuridicalPersonRepository repository, NaturalPersonRepository naturalPersonRepository) {
        this.repository = repository;
        this.naturalPersonRepository = naturalPersonRepository;
    }

    public JuridicalPersonDTO merge(long juridicalPersonId, long naturalPersonId) {
        JuridicalPerson juridicalPerson = repository.findById(juridicalPersonId)
                .orElseThrow(() -> new EntityNotFoundException("Juridical person with id=" + juridicalPersonId + " does not exist"));
        NaturalPerson naturalPerson = naturalPersonRepository.findById(naturalPersonId)
                .orElseThrow(() -> new EntityNotFoundException("Natural person with id=" + naturalPersonId + " was not found"));

        Collection<Contact> contacts = naturalPerson.getContacts()
                .stream()
                .filter(contact -> contact.getLabel() == Label.WORK_MAIL || contact.getLabel() == Label.WORK_NUM)
                .collect(Collectors.toList());

        if (contacts.isEmpty()) {
            throw new MergeException("Natural person does not have neither work mail, nor work number");
        }

        Contactor contactor = new Contactor();
        contactor.setName(String.join(" ", naturalPerson.getName(), naturalPerson.getSurname()));
        contactor.setComment(naturalPerson.getComment());
        contactor.setBirthDate(naturalPerson.getBirthDate());
        contactor.setContacts(contacts);

        juridicalPerson.getContactors().add(contactor);
        return update(juridicalPersonId, JuridicalPersonMapper.entityToDTO(juridicalPerson));
    }

    public Optional<JuridicalPersonDTO> findById(long id) {
        JuridicalPersonDTO dto = JuridicalPersonMapper.entityToDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Juridical person with id=" + id + " does not exist")));
        return Optional.of(dto);
    }

    public Collection<JuridicalPersonDTO> findALl() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(JuridicalPersonMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    public JuridicalPersonDTO save(JuridicalPersonDTO dto) {
        JuridicalPerson entity = JuridicalPersonMapper.dtoToEntity(dto);
        JuridicalPerson save = repository.save(entity);
        return JuridicalPersonMapper.entityToDTO(save);
    }

    public JuridicalPersonDTO update(long id, JuridicalPersonDTO dto) {
        JuridicalPerson person = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Juridical person with id=" + id + " not found"));
        JuridicalPerson entity = JuridicalPersonMapper.dtoToEntity(dto);
        entity.setId(id);
        Collection<Contactor> newContactors = new ArrayList<>();
        for (Contactor contactor : person.getContactors()) {
            if (!entity.getContactors().contains(contactor)) {
                newContactors.add(contactor);
            }
        }
        newContactors.addAll(entity.getContactors());
        entity.setContactors(newContactors);
        System.out.println(newContactors);
        JuridicalPerson save = repository.save(entity);
        return JuridicalPersonMapper.entityToDTO(save);
    }

    public void delete(long id) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Juridical person with id=" + id + " was not found"));
        repository.deleteById(id);
    }
}

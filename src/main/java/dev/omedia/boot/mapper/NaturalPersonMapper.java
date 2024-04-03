package dev.omedia.boot.mapper;

import dev.omedia.boot.domain.Contact;
import dev.omedia.boot.domain.NaturalPerson;
import dev.omedia.boot.dto.ContactDTO;
import dev.omedia.boot.dto.NaturalPersonDTO;

import java.util.*;
import java.util.stream.Collectors;

public class NaturalPersonMapper {
    public static NaturalPerson dtoToEntity(NaturalPersonDTO dto) {
        NaturalPerson entity = new NaturalPerson();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setComment(dto.getComment());
        entity.setBirthDate(dto.getBirthDate());

        if (!Objects.isNull(dto.getContacts())) {
            Collection<Contact> contacts = dto.getContacts().stream()
                    .map(ContactMapper::dtoToEntity)
                    .collect(Collectors.toList());
            entity.setContacts(contacts);
        } else {
            entity.setContacts(new ArrayList<>());
        }

        return entity;
    }

    public static NaturalPersonDTO entityToDTO(NaturalPerson entity) {
        NaturalPersonDTO dto = new NaturalPersonDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setComment(entity.getComment());
        dto.setBirthDate(entity.getBirthDate());

        if (!Objects.isNull(entity.getContacts())) {
            Collection<ContactDTO> contactDTOs = entity.getContacts().stream()
                    .map(ContactMapper::entityToDTO)
                    .collect(Collectors.toList());
            dto.setContacts(contactDTOs);
        } else {
            dto.setContacts(new ArrayList<>());
        }

        return dto;
    }
}

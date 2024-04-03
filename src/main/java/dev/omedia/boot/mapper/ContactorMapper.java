package dev.omedia.boot.mapper;

import dev.omedia.boot.domain.Contact;
import dev.omedia.boot.domain.Contactor;
import dev.omedia.boot.dto.ContactDTO;
import dev.omedia.boot.dto.ContactorDTO;

import java.util.*;
import java.util.stream.Collectors;

public class ContactorMapper {

    public static Contactor dtoToEntity(ContactorDTO dto) {
        Contactor entity = new Contactor();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
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

    public static ContactorDTO entityToDTO(Contactor entity) {
        ContactorDTO dto = new ContactorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
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

package dev.omedia.boot.mapper;

import dev.omedia.boot.domain.Contactor;
import dev.omedia.boot.domain.JuridicalPerson;
import dev.omedia.boot.dto.ContactorDTO;
import dev.omedia.boot.dto.JuridicalPersonDTO;

import java.util.*;
import java.util.stream.Collectors;

public class JuridicalPersonMapper {
    public static JuridicalPerson dtoToEntity(JuridicalPersonDTO dto) {
        JuridicalPerson entity = new JuridicalPerson();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setComment(dto.getComment());

        if (!Objects.isNull(dto.getContactors())) {
            Collection<Contactor> contactors = dto.getContactors().stream()
                    .map(ContactorMapper::dtoToEntity)
                    .collect(Collectors.toList());
            entity.setContactors(contactors);
        } else {
            entity.setContactors(new ArrayList<>());
        }

        return entity;
    }

    public static JuridicalPersonDTO entityToDTO(JuridicalPerson entity) {
        JuridicalPersonDTO dto = new JuridicalPersonDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setComment(entity.getComment());

        if (!Objects.isNull(entity.getContactors())) {
            Collection<ContactorDTO> contactorDTOs = entity.getContactors().stream()
                    .map(ContactorMapper::entityToDTO)
                    .collect(Collectors.toList());
            dto.setContactors(contactorDTOs);
        } else {
            dto.setContactors(new ArrayList<>());
        }

        return dto;
    }
}

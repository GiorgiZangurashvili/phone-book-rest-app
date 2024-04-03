package dev.omedia.boot.mapper;

import dev.omedia.boot.domain.Contact;
import dev.omedia.boot.dto.ContactDTO;

public class ContactMapper {

    public static Contact dtoToEntity(ContactDTO dto) {
        Contact entity = new Contact();
        entity.setId(dto.getId());
        entity.setLabel(dto.getLabel());
        entity.setValue(dto.getValue());

        return entity;
    }

    public static ContactDTO entityToDTO(Contact entity) {
        var dto = new ContactDTO();

        dto.setId(entity.getId());
        dto.setLabel(entity.getLabel());
        dto.setValue(entity.getValue());

        return dto;
    }
}

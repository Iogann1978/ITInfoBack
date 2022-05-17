package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.home.itinfo.dto.PublisherDTO;
import ru.home.itinfo.model.Publisher;

@Mapper(componentModel = "spring")
public interface PublisherMapper extends CommonMapper<PublisherDTO, Publisher> {
    @Mappings({
            @Mapping(target = "count", expression = "java(entity.getInfos() != null ? entity.getInfos().size() : 0)")
    })
    PublisherDTO entityToDto(Publisher entity);
}

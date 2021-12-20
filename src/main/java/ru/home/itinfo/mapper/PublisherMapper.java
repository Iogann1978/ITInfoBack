package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.PublisherDTO;
import ru.home.itinfo.model.Publisher;

@Mapper(componentModel = "spring")
public interface PublisherMapper extends CommonMapper<PublisherDTO, Publisher> {
}

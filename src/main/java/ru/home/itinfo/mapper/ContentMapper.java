package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.ContentDTO;
import ru.home.itinfo.model.Content;

@Mapper(componentModel = "spring")
public interface ContentMapper extends CommonMapper<ContentDTO, Content> {
}

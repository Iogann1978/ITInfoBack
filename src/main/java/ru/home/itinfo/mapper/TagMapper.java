package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.TagDTO;
import ru.home.itinfo.model.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper extends CommonMapper<TagDTO, Tag> {
}

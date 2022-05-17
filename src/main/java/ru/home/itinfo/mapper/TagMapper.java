package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.home.itinfo.dto.TagDTO;
import ru.home.itinfo.model.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper extends CommonMapper<TagDTO, Tag> {
    @Mappings({
            @Mapping(target = "count", expression = "java(entity.getInfos() != null ? entity.getInfos().size() : 0)")
    })
    TagDTO entityToDto(Tag entity);
}

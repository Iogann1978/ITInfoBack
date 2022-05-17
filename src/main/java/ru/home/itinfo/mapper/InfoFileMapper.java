package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.home.itinfo.dto.InfoFileDTO;
import ru.home.itinfo.model.InfoFile;

@Mapper(componentModel = "spring")
public interface InfoFileMapper extends CommonMapper<InfoFileDTO, InfoFile> {
    @Mappings({
            @Mapping(target = "title", source = "entity.info.title")
    })
    InfoFileDTO entityToDto(InfoFile entity);
}

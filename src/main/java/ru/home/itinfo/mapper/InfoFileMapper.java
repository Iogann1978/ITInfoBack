package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.InfoFileDTO;
import ru.home.itinfo.model.InfoFile;

@Mapper(componentModel = "spring")
public interface InfoFileMapper extends CommonMapper<InfoFileDTO, InfoFile> {
}

package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.model.Info;

@Mapper(componentModel = "spring")
public interface InfoMapper extends CommonMapper<InfoDTO, Info> {
}

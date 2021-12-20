package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.model.Descript;

@Mapper(componentModel = "spring")
public interface DescriptMapper extends CommonMapper<DescriptDTO, Descript> {
}

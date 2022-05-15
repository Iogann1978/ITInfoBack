package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.model.Descript;

@Mapper(componentModel = "spring")
public interface DescriptMapper extends CommonMapper<DescriptDTO, Descript> {
    @Mappings({
            @Mapping(target = "infoId", source = "entity.info.id")
    })
    DescriptDTO entityToDto(Descript entity);
    @Mappings({
            @Mapping(target="info.id", source="dto.infoId"),
    })
    Descript dtoToEntity(DescriptDTO dto);
}

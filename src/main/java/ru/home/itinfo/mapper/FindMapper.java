package ru.home.itinfo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.dto.CourseDTO;
import ru.home.itinfo.dto.FindDTO;
import ru.home.itinfo.model.Info;
import ru.home.itinfo.service.BookService;

@Mapper(componentModel = "spring")
public interface FindMapper {
    @Mappings(
        @Mapping(target = "type", expression = "java(ru.home.itinfo.dto.FindDTO.FindType.BOOK)")
    )
    FindDTO fromInfoDTO(BookDTO bookDTO);
    @Mappings(
            @Mapping(target = "type", expression = "java(ru.home.itinfo.dto.FindDTO.FindType.COURSE)")
    )
    FindDTO fromInfoDTO(CourseDTO courseDTO);
    @Mappings(
            @Mapping(target = "type", expression = "java(bookService.existsById(info.getId()) ? ru.home.itinfo.dto.FindDTO.FindType.BOOK : ru.home.itinfo.dto.FindDTO.FindType.COURSE)")
    )
    FindDTO fromInfoDTO(Info info, BookService bookService);
}

package ru.home.itinfo.mapper;

import lombok.Cleanup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import ru.home.itinfo.dto.AuthorDTO;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.dto.PublisherDTO;
import ru.home.itinfo.dto.google.VolumeInfoDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VolumeMapper {
    @Mappings({
            @Mapping(target = "pages", source = "dto.pageCount"),
            @Mapping(target = "title", expression = "java(dto.getTitle() + \" \" + dto.getSubtitle())"),
            @Mapping(target = "publisher", source = "dto.publisher", qualifiedByName = "toPublisher"),
            @Mapping(target = "year", source = "dto.publishedDate", qualifiedByName = "toYear"),
            @Mapping(target = "descript", source = "dto.description", qualifiedByName = "toDescript"),
            @Mapping(target = "authors", source = "dto.authors", qualifiedByName = "toAuthors")
    })
    BookDTO volumeToBook(VolumeInfoDTO dto);

    @Named("toPublisher")
    static PublisherDTO toPublisher(String publisher) {
        return PublisherDTO.builder().name(publisher).build();
    }

    @Named("toYear")
    static int toYear(LocalDate publishedDate) {
        return publishedDate.getYear();
    }

    @Named("toDescript")
    static DescriptDTO toDescript(String description) throws IOException {
        @Cleanup
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        @Cleanup
        PrintWriter pw = new PrintWriter(baos);
        pw.println("<html>");
        pw.println("<body>");
        pw.println("<p>");
        pw.println(description);
        pw.println("</p>");
        pw.println("</body>");
        pw.println("</html>");
        return DescriptDTO.builder().text(baos.toByteArray()).build();
    }

    @Named("toAuthors")
    static Set<AuthorDTO> toAuthors(List<String> authors) {
        return authors.stream().map(a -> AuthorDTO.builder().name(a).build()).collect(Collectors.toSet());
    }
}

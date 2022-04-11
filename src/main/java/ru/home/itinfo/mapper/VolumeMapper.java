package ru.home.itinfo.mapper;

import lombok.Cleanup;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;
import ru.home.itinfo.dto.*;
import ru.home.itinfo.dto.google.VolumeInfoDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface VolumeMapper {
    @Mappings({
            @Mapping(target = "pages", source = "dto.pageCount"),
            @Mapping(target = "title", expression = "java(java.util.stream.Stream.of(dto.getTitle(), dto.getSubtitle()).filter(org.apache.commons.lang3.StringUtils::isNotEmpty).collect(java.util.stream.Collectors.joining(\" \")))"),
            @Mapping(target = "publisher", source = "dto.publisher", qualifiedByName = "toPublisher"),
            @Mapping(target = "year", source = "dto.publishedDate", qualifiedByName = "toYear"),
            @Mapping(target = "descript", source = "dto.description", qualifiedByName = "toDescript"),
            @Mapping(target = "authors", source = "dto.authors", qualifiedByName = "toAuthors"),
            @Mapping(target = "tags", source = "dto.categories", qualifiedByName = "toTags")
    })
    BookDTO volumeToBook(VolumeInfoDTO dto);

    @Named("toPublisher")
    static PublisherDTO toPublisher(String publisher) {
        return PublisherDTO.builder().name(publisher).build();
    }

    @Named("toYear")
    static int toYear(String publishedDate) {
        return StringUtils.isNumeric(publishedDate) ? Integer.valueOf(publishedDate) :
                LocalDate.parse(publishedDate).getYear();
    }

    @Named("toDescript")
    static DescriptDTO toDescript(String description) throws IOException {
        @Cleanup
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        @Cleanup
        PrintWriter pw = new PrintWriter(baos);
        pw.println("<html>\n");
        pw.println("\t<body>\n");
        pw.println("\t\t<p>\n");
        pw.println(description);
        pw.println("\t\t</p>\n");
        pw.println("\t</body>\n");
        pw.println("</html>");
        return DescriptDTO.builder().text(baos.toByteArray()).build();
    }

    @Named("toAuthors")
    static Set<AuthorDTO> toAuthors(List<String> authors) {
        return authors.stream().map(a -> AuthorDTO.builder().name(a).build()).collect(Collectors.toSet());
    }

    @Named("toTags")
    static Set<TagDTO> toTags(List<String> categories) {
        return !CollectionUtils.isEmpty(categories) ?
                categories.stream().map(c -> TagDTO.builder().tag(c).build()).collect(Collectors.toSet()) :
                Collections.EMPTY_SET;
    }
}

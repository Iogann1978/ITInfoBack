package ru.home.itinfo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.dto.FindDTO;
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.mapper.FindMapper;
import ru.home.itinfo.service.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/find")
@Tag(name = "FindController", description = "Контроллер для поиска")
public class FindController {
    private final InfoService infoService;
    private final PublisherService publisherService;
    private final TagService tagService;
    private final AuthorService authorService;
    private final DescriptService descriptService;
    private final BookService bookService;
    private final FindMapper findMapper;

    @GetMapping("/info")
    @Operation(summary = "Получить список найденых курсов и книг")
    public List<FindDTO> findInfo(
            @Parameter(description = "Название")
            @RequestParam(required = false) String title,
            @Parameter(description = "Описание")
            @RequestParam(required = false)  String descript,
            @Parameter(description = "Тэг")
            @RequestParam(required = false)  String tag,
            @Parameter(description = "Издатель")
            @RequestParam(required = false)  String publisher
    ) {
        List<InfoDTO> list = Collections.EMPTY_LIST;
        if (StringUtils.isNotEmpty(title)) {
            list = infoService.findByTitle(title);
        } else if (StringUtils.isNotEmpty(descript)) {
            list = descriptService.findByText(descript);
        } else if (StringUtils.isNotEmpty(tag)) {
            list = tagService.findByTag(tag);
        } else if (StringUtils.isNotEmpty(publisher)) {
            list = publisherService.findByPublisher(publisher);
        }
        return list.stream().map(findMapper::fromInfoDTO).collect(Collectors.toList());
    }

    @GetMapping("/book")
    @Operation(summary = "Получить список найденых книг")
    public List<FindDTO> findBook(
            @Parameter(description = "Автор")
            @RequestParam(required = false)  String author,
            @Parameter(description = "ISBN")
            @RequestParam(required = false)  String isbn
    ) {
        List<BookDTO> list = Collections.EMPTY_LIST;
        if (StringUtils.isNotEmpty(author)) {
            list = authorService.findByAuthor(author);
        } else if (StringUtils.isNotEmpty(isbn)) {
            list = bookService.findByIsbn(isbn);
        }
        return list.stream().map(findMapper::fromBook).collect(Collectors.toList());
    }
}

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
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.service.AuthorService;
import ru.home.itinfo.service.InfoService;
import ru.home.itinfo.service.PublisherService;
import ru.home.itinfo.service.TagService;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/find")
@Tag(name = "FindController", description = "Контроллер для поиска")
public class FindController {
    private final InfoService infoService;
    private final PublisherService publisherService;
    private final TagService tagService;
    private final AuthorService authorService;

    @GetMapping("/info")
    @Operation(summary = "Получить список авторов")
    public List<InfoDTO> findInfo(
            @Parameter(description = "Название")
            @RequestParam String title,
            @Parameter(description = "Содержание")
            @RequestParam String content,
            @Parameter(description = "Описание")
            @RequestParam String descript,
            @Parameter(description = "Автор")
            @RequestParam String author,
            @Parameter(description = "Тэг")
            @RequestParam String tag,
            @Parameter(description = "Издатель")
            @RequestParam String publisher
    ) {
        List<InfoDTO> list = Collections.EMPTY_LIST;
        if (StringUtils.isNotEmpty(title)) {
            list = infoService.findByTitle(title);
        } else if (StringUtils.isNotEmpty(content)) {

        } else if (StringUtils.isNotEmpty(descript)) {

        } else if (StringUtils.isNotEmpty(tag)) {
            list = tagService.findByTag(tag);
        } else if (StringUtils.isNotEmpty(publisher)) {
            list = publisherService.findByPublisher(publisher);
        }
        return list;
    }

    @GetMapping("/book")
    @Operation(summary = "Получить список авторов")
    public List<BookDTO> findBook(
            @Parameter(description = "Автор")
            @RequestParam String author
    ) {
        List<BookDTO> list = Collections.EMPTY_LIST;
        if (StringUtils.isNotEmpty(author)) {
            list = authorService.findByAuthor(author);
        }
        return list;
    }
}

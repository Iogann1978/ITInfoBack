package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.FindDTO;
import ru.home.itinfo.dto.TagDTO;
import ru.home.itinfo.mapper.TagMapper;
import ru.home.itinfo.model.Tag;
import ru.home.itinfo.repository.impl.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService extends CommonService<TagDTO, Tag, String> {
    private final TagRepository tagRepository;
    private final BookService bookService;

    @Autowired
    public TagService(
            TagRepository tagRepository,
            TagMapper tagMapper,
            BookService bookService) {
        super(tagRepository, tagMapper, "Тэг");
        this.tagRepository = tagRepository;
        this.bookService = bookService;
    }

    public Tag getTag(String tag) {
        return tagRepository.findById(tag)
                .orElse(saveEntity(Tag.builder().tag(tag).build()));
    }

    public List<FindDTO> findByTag(String tag) {
        return tagRepository.getAllByTag(tag).stream()
                .flatMap(t -> t.getInfos().stream())
                .map(info ->
                        FindDTO.builder()
                                .title(info.getTitle())
                                .type(bookService.existsById(info.getId()) ? FindDTO.FindType.BOOK : FindDTO.FindType.COURSE)
                                .build()
                ).collect(Collectors.toList());
    }
}

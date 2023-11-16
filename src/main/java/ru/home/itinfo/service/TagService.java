package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.FindDTO;
import ru.home.itinfo.dto.TagDTO;
import ru.home.itinfo.mapper.FindMapper;
import ru.home.itinfo.mapper.TagMapper;
import ru.home.itinfo.model.Tag;
import ru.home.itinfo.repository.impl.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService extends CommonService<TagDTO, Tag, String> {
    private final TagRepository tagRepository;
    private final BookService bookService;
    private final FindMapper findMapper;

    @Autowired
    public TagService(
            TagRepository tagRepository,
            TagMapper tagMapper,
            FindMapper findMapper,
            BookService bookService) {
        super(tagRepository, tagMapper, "Тэг");
        this.tagRepository = tagRepository;
        this.bookService = bookService;
        this.findMapper = findMapper;
    }

    public Tag getTag(String tag) {
        return tagRepository.findById(tag)
                .orElse(saveEntity(Tag.builder().tag(tag).build()));
    }

    public List<FindDTO> findByTag(String tag) {
        return tagRepository.getAllByTag(tag).stream()
                .flatMap(t -> t.getInfos().stream())
                .map(info -> findMapper.fromInfoDTO(info, bookService))
                .collect(Collectors.toList());
    }
}

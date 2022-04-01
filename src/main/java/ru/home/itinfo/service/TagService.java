package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.TagDTO;
import ru.home.itinfo.mapper.TagMapper;
import ru.home.itinfo.model.Tag;
import ru.home.itinfo.repository.TagRepository;

@Service
public class TagService extends CommonService<TagDTO, Tag, String> {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        super(tagRepository, tagMapper, "Тэг");
        this.tagRepository = tagRepository;
    }

    public Tag getTag(String tag) {
        return tagRepository.findById(tag)
                .orElse(saveEntity(Tag.builder().tag(tag).build()));
    }
}

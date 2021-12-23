package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.TagDTO;
import ru.home.itinfo.mapper.TagMapper;
import ru.home.itinfo.model.Tag;
import ru.home.itinfo.repository.TagRepository;

@Service
public class TagService extends CommonService<TagDTO, Tag, Long> {
    @Autowired
    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        super(tagRepository, tagMapper, "Тэг");
    }
}

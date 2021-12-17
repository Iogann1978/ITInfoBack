package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.model.Tag;
import ru.home.itinfo.repository.TagRepository;

@Service
public class TagService extends CommonService<Tag, Long> {
    @Autowired
    public TagService(TagRepository tagRepository) {
        super(tagRepository);
    }
}

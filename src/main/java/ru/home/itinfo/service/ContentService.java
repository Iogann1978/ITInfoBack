package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.model.Content;
import ru.home.itinfo.repository.ContentRepository;

@Service
public class ContentService extends CommonService<Content, Long> {
    @Autowired
    public ContentService(ContentRepository contentRepository) {
        super(contentRepository);
    }
}

package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.dto.TagDTO;
import ru.home.itinfo.mapper.InfoMapper;
import ru.home.itinfo.mapper.TagMapper;
import ru.home.itinfo.model.Tag;
import ru.home.itinfo.repository.impl.TagRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService extends CommonService<TagDTO, Tag, String> {
    private final TagRepository tagRepository;
    private final InfoMapper infoMapper;

    @Autowired
    public TagService(
            TagRepository tagRepository,
            TagMapper tagMapper,
            InfoMapper infoMapper) {
        super(tagRepository, tagMapper, "Тэг");
        this.tagRepository = tagRepository;
        this.infoMapper = infoMapper;
    }

    public Tag getTag(String tag) {
        return tagRepository.findById(tag)
                .orElse(saveEntity(Tag.builder().tag(tag).build()));
    }

    public List<InfoDTO> findByTag(String tag) {
        return tagRepository.getAllByTagLike(tag).stream()
                .flatMap(t -> t.getInfos().stream()).map(infoMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

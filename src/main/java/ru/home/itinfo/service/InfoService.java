package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.mapper.InfoMapper;
import ru.home.itinfo.model.Info;
import ru.home.itinfo.repository.impl.InfoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoService extends CommonService<InfoDTO, Info, Long> {
    private final InfoRepository infoRepository;
    private final InfoMapper infoMapper;

    @Autowired
    public InfoService(InfoRepository infoRepository, InfoMapper infoMapper) {
        super(infoRepository, infoMapper, "Общая информация");
        this.infoRepository = infoRepository;
        this.infoMapper = infoMapper;
    }

    public List<InfoDTO> findByTitle(String title) {
        return infoRepository.getAllByTitleLike(title).stream()
                .map(infoMapper::entityToDto).collect(Collectors.toList());
    }
}

package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.mapper.DescriptMapper;
import ru.home.itinfo.mapper.InfoMapper;
import ru.home.itinfo.model.Descript;
import ru.home.itinfo.repository.impl.DescriptRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DescriptService extends CommonService<DescriptDTO, Descript, Long> {
    private final DescriptRepository descriptRepository;
    private final InfoMapper infoMapper;

    @Autowired
    public DescriptService(
            DescriptRepository descriptRepository,
            DescriptMapper descriptMapper,
            InfoMapper infoMapper) {
        super(descriptRepository, descriptMapper, "Описание");
        this.descriptRepository = descriptRepository;
        this.infoMapper = infoMapper;
    }

    public List<InfoDTO> findByText(String text) {
        return descriptRepository.findAllByTextLike(text).stream()
                .map(Descript::getInfo).map(infoMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

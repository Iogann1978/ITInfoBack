package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.InfoDTO;
import ru.home.itinfo.mapper.InfoMapper;
import ru.home.itinfo.model.Info;
import ru.home.itinfo.repository.impl.InfoRepository;

@Service
public class InfoService extends CommonService<InfoDTO, Info, Long> {

    @Autowired
    public InfoService(InfoRepository infoRepository, InfoMapper infoMapper) {
        super(infoRepository, infoMapper, "Общая информация");
    }
}

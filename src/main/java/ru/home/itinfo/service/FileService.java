package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.InfoFileDTO;
import ru.home.itinfo.mapper.InfoFileMapper;
import ru.home.itinfo.model.InfoFile;
import ru.home.itinfo.repository.impl.FileRepository;

@Service
public class FileService extends CommonService<InfoFileDTO, InfoFile, Long> {
    @Autowired
    public FileService(FileRepository fileRepository, InfoFileMapper infoFileMapper) {
        super(fileRepository, infoFileMapper, "Файл");
    }
}

package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.model.InfoFile;
import ru.home.itinfo.repository.FileRepository;

@Service
public class FileService extends CommonService<InfoFile, Long> {
    @Autowired
    public FileService(FileRepository fileRepository) {
        super(fileRepository);
    }
}

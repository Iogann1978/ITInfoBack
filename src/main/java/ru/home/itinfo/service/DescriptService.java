package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.dto.FindDTO;
import ru.home.itinfo.mapper.DescriptMapper;
import ru.home.itinfo.model.Descript;
import ru.home.itinfo.repository.impl.DescriptRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DescriptService extends CommonService<DescriptDTO, Descript, Long> {
    private final DescriptRepository descriptRepository;
    private final BookService bookService;

    @Autowired
    public DescriptService(
            DescriptRepository descriptRepository,
            DescriptMapper descriptMapper,
            BookService bookService) {
        super(descriptRepository, descriptMapper, "Описание");
        this.descriptRepository = descriptRepository;
        this.bookService = bookService;
    }

    public List<FindDTO> findByText(String text) {
        return descriptRepository.findAllByTextLike(text).stream()
                .map(Descript::getInfo).map(info ->
                        FindDTO.builder()
                                .title(info.getTitle())
                                .type(bookService.existsById(info.getId()) ? FindDTO.FindType.BOOK : FindDTO.FindType.COURSE)
                                .build()
                ).collect(Collectors.toList());
    }
}

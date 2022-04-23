package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.itinfo.dto.DescriptDTO;
import ru.home.itinfo.exception.NotFoundException;
import ru.home.itinfo.mapper.DescriptMapper;
import ru.home.itinfo.model.Descript;
import ru.home.itinfo.repository.BookRepository;
import ru.home.itinfo.repository.CourseRepository;
import ru.home.itinfo.repository.DescriptRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DescriptService extends CommonService<DescriptDTO, Descript, Long> {
    private final BookRepository bookRepository;
    private final CourseRepository courseRepository;
    private final DescriptMapper descriptMapper;

    @Autowired
    public DescriptService(
            BookRepository bookRepository,
            CourseRepository courseRepository,
            DescriptRepository descriptRepository,
            DescriptMapper descriptMapper) {
        super(descriptRepository, descriptMapper, "Описание");
        this.bookRepository = bookRepository;
        this.courseRepository = courseRepository;
        this.descriptMapper = descriptMapper;
    }

    public List<DescriptDTO> getFromBook(Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> book.getDescripts().stream().map(descriptMapper::entityToDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new NotFoundException(String.format("Книга с id=%d не найдена", bookId)));
    }

    public List<DescriptDTO> getFromCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .map(book -> book.getDescripts().stream().map(descriptMapper::entityToDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new NotFoundException(String.format("Курс с id=%d не найдена", courseId)));
    }
}

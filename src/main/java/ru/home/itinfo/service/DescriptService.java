package ru.home.itinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.itinfo.exception.NotFoundException;
import ru.home.itinfo.repository.BookRepository;
import ru.home.itinfo.repository.CourseRepository;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class DescriptService {
    private final BookRepository bookRepository;
    private final CourseRepository courseRepository;

    public String getFromBook(Long bookId) {
        return bookRepository.findById(bookId)
                .map(book -> new String(book.getDescript().getText(), StandardCharsets.UTF_8))
                .orElseThrow(() -> new NotFoundException(String.format("Книга с id=%d не найдена", bookId)));
    }

    public String getFromCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .map(course -> new String(course.getDescript().getText(), StandardCharsets.UTF_8))
                .orElseThrow(() -> new NotFoundException(String.format("Курс с id=%d не найдена", courseId)));
    }
}

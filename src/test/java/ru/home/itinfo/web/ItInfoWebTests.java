package ru.home.itinfo.web;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.home.itinfo.controller.*;
import ru.home.itinfo.model.Descript;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {
        AuthorController.class,
        BookController.class,
        CourseController.class,
        DescriptController.class,
        ExceptionController.class,
        FileController.class,
        InfoController.class,
        TagController.class
})
public class ItInfoWebTests {
}

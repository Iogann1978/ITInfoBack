package ru.home.itinfo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.home.itinfo.controller.*;
import ru.home.itinfo.dto.*;
import ru.home.itinfo.service.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
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
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;
    @MockBean
    private BookService bookService;
    @MockBean
    private CourseService courseService;
    @MockBean
    private DescriptService descriptService;
    @MockBean
    private FileService fileService;
    @MockBean
    private InfoService infoService;
    @MockBean
    private TagService tagService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void authorTest() throws Exception {
        mockMvc.perform(get("/author")).andExpect(status().isOk());
        mockMvc.perform(get("/author/1")).andExpect(status().isOk());
        mockMvc.perform(
                post("/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                AuthorDTO.builder().name("test author").build()
                        ))
        ).andExpect(status().isCreated());
        mockMvc.perform(delete("/author/1")).andExpect(status().isOk());
    }

    @Test
    void bookTest() throws Exception {
        mockMvc.perform(get("/book")).andExpect(status().isOk());
        mockMvc.perform(get("/book/1")).andExpect(status().isOk());
        mockMvc.perform(
                post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                BookDTO.builder().title("test book").build()
                        ))
        ).andExpect(status().isCreated());
        mockMvc.perform(delete("/book/1")).andExpect(status().isOk());
    }

    @Test
    void courseTest() throws Exception {
        mockMvc.perform(get("/course")).andExpect(status().isOk());
        mockMvc.perform(get("/course/1")).andExpect(status().isOk());
        mockMvc.perform(
                post("/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                CourseDTO.builder().title("test course").build()
                        ))
        ).andExpect(status().isCreated());
        mockMvc.perform(delete("/course/1")).andExpect(status().isOk());
    }

    @Test
    void descriptTest() throws Exception {
        mockMvc.perform(get("/descript/1")).andExpect(status().isOk());
        mockMvc.perform(
                post("/descript")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                DescriptDTO.builder().name("test descript").build()
                        ))
        ).andExpect(status().isCreated());
        mockMvc.perform(delete("/descript/1")).andExpect(status().isOk());
    }

    @Test
    void fileTest() throws Exception {
        mockMvc.perform(get("/file")).andExpect(status().isOk());
        mockMvc.perform(get("/file/1")).andExpect(status().isOk());
        mockMvc.perform(
                post("/file")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                InfoFileDTO.builder().filename("test file").build()
                        ))
        ).andExpect(status().isCreated());
        mockMvc.perform(delete("/file/1")).andExpect(status().isOk());
    }

    @Test
    void infoTest() throws Exception {
        mockMvc.perform(get("/info/1")).andExpect(status().isOk());
    }

    @Test
    void tagTest() throws Exception {
        mockMvc.perform(get("/tag")).andExpect(status().isOk());
        mockMvc.perform(
                post("/tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                TagDTO.builder().tag("test tag").build()
                        ))
        ).andExpect(status().isCreated());
        mockMvc.perform(delete("/tag/1")).andExpect(status().isOk());
    }
}

package ru.home.itinfo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.home.itinfo.dto.*;
import ru.home.itinfo.mapper.*;
import ru.home.itinfo.model.*;
import ru.home.itinfo.service.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
class ItInfoApplicationTests {
	@Autowired
	private AuthorService authorService;
	@Autowired
	private AuthorMapper authorMapper;
	@Autowired
	private PublisherService publisherService;
	@Autowired
	private PublisherMapper publisherMapper;
	@Autowired
	private TagService tagService;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private FileService fileService;
	@Autowired
	private InfoFileMapper infoFileMapper;
	@Autowired
	private DescriptService descriptService;
	@Autowired
	private DescriptMapper descriptMapper;
	@Autowired
	private BookService bookService;

	@Test
	void getAuthorTest() {
		Author author = authorService.getAuthor("Mariot Tsitoara");
		log.info("author: {}", author);
	}

	@Test
	void saveBookTest() {
		Author author = authorService.saveEntity(Author.builder().name("Автор").build());
		Publisher publisher = publisherService.saveEntity(Publisher.builder().name("Издатель").build());
		Tag tag = tagService.saveEntity(Tag.builder().tag("Тэг").build());
		InfoFile file = fileService.saveEntity(InfoFile.builder().filename("файл").size(3L).build());
		Descript descript = descriptService.saveEntity(Descript.builder().name("Описание").text("Текст".getBytes(StandardCharsets.UTF_8)).build());
		BookDTO dto = BookDTO.builder()
				.id(null)
				.title("Книга")
				.authors(Set.of(authorMapper.entityToDto(author)))
				.publisher(publisherMapper.entityToDto(publisher))
				.pages(3)
				.year(2019)
				.rate(RateDTO.GOOD)
				.state(StateDTO.PLANNED)
				.file(infoFileMapper.entityToDto(file))
				.tags(Set.of(tagMapper.entityToDto(tag)))
				.descripts(List.of(descriptMapper.entityToDto(descript)))
				.build();
		bookService.save(dto);
	}
}

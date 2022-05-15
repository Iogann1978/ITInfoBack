package ru.home.itinfo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.home.itinfo.dto.*;
import ru.home.itinfo.mapper.*;
import ru.home.itinfo.model.*;
import ru.home.itinfo.service.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
//@Transactional
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
	void bookSaveTest() {
		Author author = Author.builder()
				.name("test author")
				.build();
		Publisher publisher = Publisher.builder()
				.name("test publisher")
				.build();
		InfoFile file = InfoFile.builder()
				.filename("test_file.txt")
				.size(321L)
				.build();
		Descript descript = Descript.builder()
				.name("test descript")
				.text("Test".getBytes(StandardCharsets.UTF_8))
				.build();
		Tag tag = Tag.builder().tag("Java").build();
		Book book = Book.builder()
				.isbn("1234567890")
				.title("Test book")
				.pages(3)
				.rate(Rate.GOOD)
				.state(State.PLANNED)
				.year(2007)
				.authors(Set.of(author))
				.publisher(publisher)
				.file(file)
				.tags(Set.of(tag))
				.descripts(List.of(descript))
				.build();

		Book savedBook = bookService.saveEntity(book);
		Assertions.assertNotNull(savedBook, "savedBook is null");
		log.info("book: {}", savedBook);
		log.info("author: {}", author);
		log.info("publisher: {}", publisher);
		log.info("tag: {}", tag);
		log.info("descript: {}", descript);
	}

	@Test
	void deleteDescriptTest() {
		List<DescriptDTO> descripts = descriptService.getAll();
		Assertions.assertNotNull(descripts, "descripts is null");
		Assertions.assertFalse(CollectionUtils.isEmpty(descripts), "descriptions is empty");

		DescriptDTO descript = descripts.get(0);
		log.info("descript: {}", descript);
		Assertions.assertNotNull(descript, "descript is null");
		Assertions.assertNotNull(descript.getId(), "descript.id is null");
		descriptService.delete(descript.getId());

		Assertions.assertNotNull(descript.getInfoId(), "descript.infoId is null");
		BookDTO bookDTO = bookService.get(descript.getInfoId());
		Assertions.assertNotNull(bookDTO, "bookDTO is null");
		Assertions.assertNotNull(bookDTO.getDescripts(), "bookDTO.descripts is null");
		log.info("bookDTO: {}", bookDTO);
		log.info("bookDTO.descripts: {}", bookDTO.getDescripts());
	}
}

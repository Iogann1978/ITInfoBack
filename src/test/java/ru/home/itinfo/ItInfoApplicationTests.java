package ru.home.itinfo;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
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
@Transactional
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
		Book book = Book.builder()
				.isbn("1234567890")
				.title("Test book")
				.pages(3)
				.rate(Rate.GOOD)
				.state(State.PLANNED)
				.year(2007)
				.build();
		Book savedBook = bookService.saveEntity(book);
		Assertions.assertNotNull(savedBook, "savedBook is null");

		Author author = Author.builder()
				.name("test author")
				.books(Set.of(savedBook))
				.build();
		Author savedAuthor = authorService.saveEntity(author);

		Publisher publisher = Publisher.builder()
				.name("test publisher")
				.infos(Set.of(savedBook))
				.build();
		Publisher savedPublisher = publisherService.saveEntity(publisher);

		InfoFile file = InfoFile.builder()
				.filename("test_file.txt")
				.size(321L)
				.info(savedBook)
				.build();
		InfoFile savedFile = fileService.saveEntity(file);

		Descript descript = Descript.builder()
				.name("test descript")
				.text("Test".getBytes(StandardCharsets.UTF_8))
				.info(savedBook)
				.build();
		Descript savedDescript = descriptService.saveEntity(descript);

		Tag tag = Tag.builder()
				.tag("Java")
				.infos(Set.of(savedBook))
				.build();
		Tag savedTag = tagService.saveEntity(tag);

		log.info("book: {}", savedBook);
		log.info("author: {}", savedAuthor);
		log.info("publisher: {}", savedPublisher);
		log.info("tag: {}", savedTag);
		log.info("file: {}", savedFile);
		log.info("descript: {}", savedDescript);
	}

	@Test
	void getBookTest() {
		Set<BookDTO> books = bookService.getAll();
		Assertions.assertNotNull(books, "books is null");
		Assertions.assertFalse(CollectionUtils.isEmpty(books), "books is empty");
		Assertions.assertEquals(1, books.size(), "books.size is not equals 1");

		Book dbBook = bookService.getEntity(books.stream().findFirst().map(BookDTO::getId).orElse(null));
		log.info("dbBook: {}", dbBook);
		Assertions.assertNotNull(dbBook.getDescripts(), "dbBook.descripts is null");
		Assertions.assertFalse(CollectionUtils.isEmpty(dbBook.getDescripts()), "dbBook.descripts is empty");
		Assertions.assertEquals(1, dbBook.getDescripts().size(), "dbBook.descripts is not equals 1");
		Descript dbDescript = dbBook.getDescripts().stream().findFirst().orElse(null);
		Assertions.assertNotNull(dbDescript, "dbDescript is null");
		Assertions.assertNotNull(dbDescript.getInfo(), "dbDescript.info is null");
		Assertions.assertNotNull(dbDescript.getInfo().getId(), "dbDescript.info.id is null");
		log.info("dbDescript.info.id: {}", dbDescript.getInfo().getId());
	}

	@Test
	void deleteDescriptTest() {
		Set<DescriptDTO> descripts = descriptService.getAll();
		Assertions.assertNotNull(descripts, "descripts is null");
		Assertions.assertFalse(CollectionUtils.isEmpty(descripts), "descriptions is empty");

		DescriptDTO descript = descripts.stream().findFirst().orElse(null);
		log.info("descript: {}", descript);
		Assertions.assertNotNull(descript, "descript is null");
		Assertions.assertNotNull(descript.getId(), "descript.id is null");
		descriptService.delete(descript.getId());
	}
}

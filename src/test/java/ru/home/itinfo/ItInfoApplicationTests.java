package ru.home.itinfo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.home.itinfo.model.Author;
import ru.home.itinfo.service.AuthorService;

@SpringBootTest
@Slf4j
class ItInfoApplicationTests {
	@Autowired
	private AuthorService authorService;

	@Test
	void contextLoads() {
		Author peter = authorService.getAuthor("Peter Sp\u00e4th");
		log.info("author: {}", peter);
	}

}

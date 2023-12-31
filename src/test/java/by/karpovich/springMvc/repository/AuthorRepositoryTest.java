package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.config.PersistenceConfigForTest;
import by.karpovich.springMvc.model.Author;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static by.karpovich.springMvc.config.PersistenceConfigForTest.container;
import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceConfigForTest.class, AuthorRepository.class})
class AuthorRepositoryTest {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorRepositoryTest(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @BeforeAll
    static void setup() {
        container.start();
    }

    @AfterAll
    static void close() {
        container.stop();
    }

    @Test
    void save() {
        Author result = authorRepository.save(generateAuthorEntity());

        assertNotNull(result.getId());
    }


    @Test
    void findById() {
        Author result = authorRepository.findById(1L).get();

        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "Evgeniy Karpovich");
    }


    @Test
    void deleteByIdWithConstraintViolation() {
        Long authorId = 1L;

        Optional<Author> authorOptional = authorRepository.findById(authorId);
        assertTrue(authorOptional.isPresent());

        assertThrows(DataIntegrityViolationException.class, () -> {
            authorRepository.deleteById(authorId);
            authorRepository.flush();
        });

        Optional<Author> notDeletedAuthor = authorRepository.findById(authorId);
        assertTrue(notDeletedAuthor.isPresent());
    }


    @Test
    void findByName() {
        Author result = authorRepository.findByName("Evgeniy Karpovich").get();

        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "Evgeniy Karpovich");
    }

    @Test
    void findAll() {
        List<Author> result = authorRepository.findAll();

        assertEquals(3, result.size());
    }

    @Test
    void update() {
        String updateName = "update name";
        Author entity = authorRepository.findById(3L).get();
        entity.setName(updateName);

        authorRepository.save(entity);

        Author result = authorRepository.findById(entity.getId()).get();

        assertEquals(result.getName(), updateName);
    }

    private Author generateAuthorEntity() {
        return new Author("Author test");
    }
}
package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.config.PersistenceConfigForTest;
import by.karpovich.springMvc.model.Author;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static by.karpovich.springMvc.config.PersistenceConfigForTest.container;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
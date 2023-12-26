package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.config.PersistenceConfigForTest;
import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.model.Singer;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceConfigForTest.class, SingerRepository.class})
public class SingerRepositoryTest {

    private final SingerRepository singerRepository;

    @Autowired
    public SingerRepositoryTest(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
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
        Singer result = singerRepository.save(generateSingerEntity());

        assertNotNull(result.getId());
    }

    @Test
    void findByName() {
        Singer result = singerRepository.findByName("50 CENT").get();

        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "50 CENT");
    }

    @Test
    void findById() {
        Singer actualResult = singerRepository.findById(1L).get();

        assertEquals(actualResult.getId(), 1L);
        assertEquals(actualResult.getName(), "50 CENT");
    }

    @Test
    void findAll() {
        List<Singer> all = singerRepository.findAll();

        assertEquals(3, all.size());
    }

    @Test
    void deleteById() {
        Long authorId = 1L;

        Optional<Singer> singerOptional = singerRepository.findById(authorId);
        assertTrue(singerOptional.isPresent());

        assertThrows(DataIntegrityViolationException.class, () -> {
            singerRepository.deleteById(authorId);
            singerRepository.flush();
        });

        Optional<Singer> notDeletedSinger = singerRepository.findById(authorId);
        assertTrue(notDeletedSinger.isPresent());
    }

    @Test
    void update() {
        String updateName = "update name";
        Singer entity = singerRepository.findById(3L).get();
        entity.setName(updateName);
        singerRepository.save(entity);

        Singer result = singerRepository.findById(entity.getId()).get();

        assertEquals(result.getName(), updateName);
    }

    private Singer generateSingerEntity() {
        return new Singer("Singer test");
    }
}

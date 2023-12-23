package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.config.PersistenceConfigForTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static by.karpovich.springMvc.config.PersistenceConfigForTest.container;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceConfigForTest.class, SingerRepository.class, AuthorRepository.class, SongRepository.class})
public class SingerRepositoryTest {

    private final SingerRepository singerRepository;
    private final AuthorRepository authorRepository;
    private final SongRepository songRepository;

    @Autowired
    public SingerRepositoryTest(SingerRepository singerRepository, AuthorRepository authorRepository, SongRepository songRepository) {
        this.singerRepository = singerRepository;
        this.authorRepository = authorRepository;
        this.songRepository = songRepository;
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

    }

}

package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.config.PersistenceConfigForTest;
import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.model.Song;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.karpovich.springMvc.config.PersistenceConfigForTest.container;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceConfigForTest.class, SingerRepository.class, AuthorRepository.class, SongRepository.class})
class SongRepositoryTest {

    private final SingerRepository singerRepository;
    private final AuthorRepository authorRepository;
    private final SongRepository songRepository;

    @Autowired
    public SongRepositoryTest(SingerRepository singerRepository, AuthorRepository authorRepository, SongRepository songRepository) {
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
    void findById() {
        Song result = songRepository.findById(1L).get();

        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "THE BEST SONG");
    }

    @Test
    void findByName() {
        Song result = songRepository.findByName("THE BEST SONG").get();

        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "THE BEST SONG");
    }

    @Test
    void findAll() {
        List<Song> result = songRepository.findAll();

        assertEquals(3, result.size());
    }

    @Test
    void save() {
        Song result = songRepository.save(generateSong());

        assertNotNull(result.getId());
    }

    @Test
    void update() {
        String updateName = "update name";

        Song Song = songRepository.findById(3L).get();
        Song.setName(updateName);
        songRepository.save(Song);

        Song result = songRepository.findById(Song.getId()).get();

        assertEquals(result.getName(), updateName);
    }

    private Song generateSong() {
        return new Song("Author test", findSingerForSong(), findAuthorsForSong());
    }

    private Singer findSingerForSong() {
        return singerRepository.findById(1L).get();
    }

    private List<Author> findAuthorsForSong() {
        List<Author> authors = new ArrayList<>();
        authors.add(authorRepository.findById(1L).get());
        authors.add(authorRepository.findById(2L).get());

        return authors;
    }
}
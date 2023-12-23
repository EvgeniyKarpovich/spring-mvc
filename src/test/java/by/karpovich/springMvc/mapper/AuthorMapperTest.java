package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.model.Song;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class AuthorMapperTest {
    private static final Long ID = 1L;
    private static final String AUTHOR_NAME = "AuthorTestName";
    private static final String SONG_NAME = "SongTestName";
    private static final Song SONG = new Song(ID, SONG_NAME);
    private static final List<Song> SONGS = List.of(SONG);

    @Mock
    private SongMapper songMapper;
    @InjectMocks
    private AuthorMapper authorMapper = new AuthorMapperImpl();

    @Test
    void mapFromDto() {
        Author result = authorMapper.mapFromDto(generateAuthorCreateDto());

        assertNull(result.getId());
        assertEquals(AUTHOR_NAME, result.getName());
    }

    @Test
    void mapFromEntity() {
        AuthorDto result = authorMapper.mapFromEntity(generateAuthorEntity());

        assertEquals(AUTHOR_NAME, result.name());
    }

    @Test
    void mapCreateDtoFromEntity() {
        AuthorCreateDto result = authorMapper.mapCreateDtoFromEntity(generateAuthorEntity());

        assertEquals(AUTHOR_NAME, result.name());
    }

//    @Test
//    void mapListCreateDtoFromEntity() {
//        List<Author> entities = Arrays.asList(generateAuthorEntity(), generateAuthorEntity());
//        List<AuthorCreateDto> result = authorMapper.mapListCreateDtoFromEntity(entities);
//
//        assertEquals(2, result.size());
//
//        for (AuthorCreateDto dto : result) {
//            assertEquals(AUTHOR_NAME, dto.name());
//        }
//    }

    @Test
    void mapListDtoFromListEntity() {
        List<Author> authorEntities = Arrays.asList(generateAuthorEntity(), generateAuthorEntity());
        List<AuthorDto> result = authorMapper.mapListDtoFromListEntity(authorEntities);

        assertEquals(2, result.size());

        for (AuthorDto dto : result) {
            assertEquals(AUTHOR_NAME, dto.name());
        }
    }

    private AuthorCreateDto generateAuthorCreateDto() {
        return new AuthorCreateDto(AUTHOR_NAME);
    }

    private Author generateAuthorEntity() {
        return new Author(
                ID,
                AUTHOR_NAME,
                SONGS);
    }
}
package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.SongCreateDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.service.impl.AuthorServiceImpl;
import by.karpovich.springMvc.service.impl.SingerServiceImpl;
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
class SongMapperTest {
    private static final Long ID = 1L;
    private static final String SINGER_NAME = "AuthorTestName";
    private static final String SONG_NAME = "SongTestName";
    private static final Singer SINGER = new Singer(ID, SINGER_NAME);

    @Mock
    private AuthorMapper authorMapper;
    @Mock
    private SingerMapper singerMapper;
    @InjectMocks
    private SongMapper songMapper = new SongMapperImpl();

    @Test
    void mapFromDto() {
        Song result = songMapper.mapFromDto(generateSongCreateDto());

        assertNull(result.getId());
        assertEquals(SONG_NAME, result.getName());
    }

    @Test
    void mapFromEntity() {
        SongDto result = songMapper.mapFromEntity(generateSongEntity());

        assertEquals(SONG_NAME, result.getName());
    }

    @Test
    void mapListDtoFromListEntity() {
        List<Song> entities = Arrays.asList(generateSongEntity(), generateSongEntity());
        List<SongDto> result = songMapper.mapListDtoFromListEntity(entities);

        assertEquals(2, result.size());

        for (SongDto dto : result) {
            assertEquals(SONG_NAME, dto.getName());
        }
    }

    private Song generateSongEntity() {
        return new Song(ID, SONG_NAME, SINGER);
    }

    private SongCreateDto generateSongCreateDto() {
        List<Long> authorsId = Arrays.asList(1L, 2L);
        Long singerId = 1L;
        return new SongCreateDto(SONG_NAME, singerId, authorsId);
    }
}
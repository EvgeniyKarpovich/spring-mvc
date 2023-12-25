package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.model.Singer;
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
class SingerMapperTest {
    private static final Long ID = 1L;
    private static final String SINGER_NAME = "SINGERTestName";
    private static final String SONG_NAME = "SongTestName";
    private static final Song SONG = new Song(ID, SONG_NAME);
    private static final List<Song> SONGS = List.of(SONG);

    @Mock
    private SongMapper songMapper;
    @InjectMocks
    private SingerMapper singerMapper = new SingerMapperImpl();

    @Test
    void mapFromDto() {
        Singer result = singerMapper.mapFromDto(generateSingerCreateDto());

        assertNull(result.getId());
        assertEquals(SINGER_NAME, result.getName());
    }

    @Test
    void mapFromEntity() {
        SingerDto result = singerMapper.mapFromEntity(generateSingerEntity());

        assertEquals(SINGER_NAME, result.name());
    }

    @Test
    void mapSingerCreateDtoFromEntity() {
        SingerCreateDto result = singerMapper.mapSingerCreateDtoFromEntity(generateSingerEntity());

        assertEquals(SINGER_NAME, result.name());
    }

    @Test
    void mapListDtoFromListEntity() {
        List<Singer> authorEntities = Arrays.asList(generateSingerEntity(), generateSingerEntity());
        List<SingerDto> result = singerMapper.mapListDtoFromListEntity(authorEntities);

        assertEquals(2, result.size());

        for (SingerDto dto : result) {
            assertEquals(SINGER_NAME, dto.name());
        }

    }

    private Singer generateSingerEntity() {
        return new Singer(ID, SINGER_NAME, SONGS);
    }

    private SingerCreateDto generateSingerCreateDto() {
        return new SingerCreateDto(SINGER_NAME);
    }
}
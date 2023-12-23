package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.SongCreateDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.SongMapper;
import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongServiceImplTest {

    @Mock
    private SongMapper songMapper;
    @Mock
    private SongRepository songRepository;
    @Mock
    private SingerServiceImpl singerService;
    @InjectMocks
    private SongServiceImpl songService;

    private static final Long ID = 1L;

    @Test
    void save() {
        Song mapped = mock(Song.class);
        Song saved = mock(Song.class);
        Singer singer = mock(Singer.class);

        SongCreateDto startDto = mock(SongCreateDto.class);

        when(songMapper.mapFromDto(any(SongCreateDto.class))).thenReturn(mapped);
        when(singerService.findSingerByIdWhichWillReturnModel(anyLong())).thenReturn(singer);
        when(songRepository.save(any(Song.class))).thenReturn(saved);

        songService.save(startDto);

        verify(songMapper).mapFromDto(startDto);
        verify(songRepository).save(mapped);
    }

    @Test
    void update() {
        Song entity = mock(Song.class);
        SongCreateDto dto = mock(SongCreateDto.class);

        when(songMapper.mapFromDto(any(SongCreateDto.class))).thenReturn(entity);

        songService.update(dto, ID);

        verify(songMapper).mapFromDto(dto);
        verify(songRepository).save(entity);
    }

    @Test
    void deleteById() {
        Song entity = mock(Song.class);

        when(songRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        songService.deleteById(ID);
    }

    @Test
    void findById() {
        Song entity = mock(Song.class);
        SongDto dto = mock(SongDto.class);

        when(songRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(songMapper.mapFromEntity(any(Song.class))).thenReturn(dto);

        SongDto result = songService.findById(ID);
        assertNotNull(result);
    }

    @Test
    void findAll() {
        List<Song> entities = new ArrayList<>();
        List<SongDto> dtos = new ArrayList<>();

        when(songRepository.findAll()).thenReturn(entities);
        when(songMapper.mapListDtoFromListEntity(anyList())).thenReturn(dtos);

        List<SongDto> result = songService.findAll();

        assertArrayEquals(result.toArray(), dtos.toArray());
    }

    @Test
    void findSongByIdWhichWillReturnModel() {
        Song entity = mock(Song.class);

        when(songRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        Song result = songService.findSongByIdWhichWillReturnModel(ID);
        assertNotNull(result);
    }

    @Test
    void findByIdThrowsException() {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class, () -> songService.findById(ID));
    }

    @Test
    void saveThrowsException() {
        when(songRepository.findByName(anyString())).thenThrow(DuplicateException.class);

        assertThrows(DuplicateException.class, () -> songRepository.findByName("TEST NAME"));
    }
}
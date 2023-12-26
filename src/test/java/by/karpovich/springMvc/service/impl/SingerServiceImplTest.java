package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.SingerMapper;
import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.repository.SingerRepository;
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
class SingerServiceImplTest {
    private static final Long ID = 1L;
    @Mock
    private SingerRepository singerRepository;
    @Mock
    private SingerMapper singerMapper;
    @InjectMocks
    private SingerServiceImpl singerService;

    @Test
    void save() {
        Singer mapped = mock(Singer.class);
        Singer saved = mock(Singer.class);

        SingerCreateDto startDto = mock(SingerCreateDto.class);

        when(singerMapper.mapFromDto(any(SingerCreateDto.class))).thenReturn(mapped);
        when(singerRepository.save(any(Singer.class))).thenReturn(saved);

        singerService.save(startDto);

        verify(singerMapper).mapFromDto(startDto);
        verify(singerRepository).save(mapped);
    }

    @Test
    void update() {
        Singer entity = mock(Singer.class);
        SingerCreateDto dto = mock(SingerCreateDto.class);

        when(singerMapper.mapFromDto(any(SingerCreateDto.class))).thenReturn(entity);

        singerService.update(dto, ID);

        verify(singerRepository).findByName(dto.name());
        verify(singerMapper).mapFromDto(dto);
        verify(singerRepository).save(entity);
    }

    @Test
    void deleteById() {
        Singer entity = mock(Singer.class);

        when(singerRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        singerService.deleteById(ID);
    }

    @Test
    void findById() {
        Singer entity = mock(Singer.class);
        SingerDto dto = mock(SingerDto.class);

        when(singerRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(singerMapper.mapFromEntity(any(Singer.class))).thenReturn(dto);

        SingerDto result = singerService.findById(ID);
        assertNotNull(result);
    }

    @Test
    void findAll() {
        List<Singer> entities = new ArrayList<>();
        List<SingerDto> dtos = new ArrayList<>();

        when(singerRepository.findAll()).thenReturn(entities);
        when(singerMapper.mapListDtoFromListEntity(anyList())).thenReturn(dtos);

        List<SingerDto> result = singerService.findAll();

        assertArrayEquals(result.toArray(), dtos.toArray());
    }

    @Test
    void findSingerByIdWhichWillReturnModel() {
        Singer Singer = mock(Singer.class);

        when(singerRepository.findById(anyLong())).thenReturn(Optional.of(Singer));

        Singer result = singerService.findSingerByIdWhichWillReturnModel(ID);
        assertEquals(result, Singer);
    }

    @Test
    void findSingerByIdWhichWillReturnModelThrowsException() {
        when(singerRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class,
                () -> singerService.findSingerByIdWhichWillReturnModel(ID));
    }

    @Test
    void saveThrowsException() {
        when(singerRepository.findByName(anyString())).thenThrow(DuplicateException.class);

        assertThrows(DuplicateException.class, () -> singerRepository.findByName("TEST NAME"));
    }

    @Test
    void whenFindByIdNotFound_ShouldThrowException() {
        Long invalidId = 100L;
        when(singerRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class, () ->
                singerService.findById(invalidId));
    }
}
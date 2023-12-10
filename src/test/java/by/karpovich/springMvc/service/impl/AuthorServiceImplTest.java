package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.AuthorMapper;
import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.repository.AuthorRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    private static final Long ID = 1L;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void save() {
        Author mapped = mock(Author.class);
        Author saved = mock(Author.class);

        AuthorCreateDto startDto = mock(AuthorCreateDto.class);

        when(authorMapper.mapFromDto(any(AuthorCreateDto.class))).thenReturn(mapped);
        when(authorRepository.save(any(Author.class))).thenReturn(saved);

        authorService.save(startDto);

        verify(authorMapper).mapFromDto(startDto);
        verify(authorRepository).save(mapped);
    }

    @Test
    void update() {
        Author entity = mock(Author.class);
        AuthorCreateDto dto = mock(AuthorCreateDto.class);

        when(authorMapper.mapFromDto(any(AuthorCreateDto.class))).thenReturn(entity);

        authorService.update(dto, ID);

        verify(authorRepository).findByName(dto.name());
        verify(authorMapper).mapFromDto(dto);
        verify(authorRepository).save(entity);
    }

    @Test
    void deleteById() {
        Author entity = mock(Author.class);

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        authorService.deleteById(ID);
    }

    @Test
    void findById() {
        Author entity = mock(Author.class);
        AuthorDto dto = mock(AuthorDto.class);

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(authorMapper.mapFromEntity(any(Author.class))).thenReturn(dto);

        AuthorDto result = authorService.findById(ID);
        assertNotNull(result);
    }

    @Test
    void findAll() {
        List<Author> entities = new ArrayList<>();
        List<AuthorDto> dtos = new ArrayList<>();

        when(authorRepository.findAll()).thenReturn(entities);
        when(authorMapper.mapListDtoFromListEntity(anyList())).thenReturn(dtos);

        List<AuthorDto> result = authorService.findAll();

        assertArrayEquals(result.toArray(), dtos.toArray());
    }

    @Test
    void findAuthorByIdWhichWillReturnModel() {
        Author entity = mock(Author.class);

        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(entity));

        Author result = authorService.findAuthorByIdWhichWillReturnModel(ID);
        assertNotNull(result);
    }

    @Test
    void findByIdReturnFullDtoThrowException() {
        assertThrows(NotFoundEntityException.class, () -> authorService.findById(ID));

        verify(authorRepository).findById(ID);
    }

    @Test
    void findByIdThrowsException() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundEntityException.class, () -> authorService.findById(ID));
    }

    @Test
    void saveThrowsException() {
        when(authorRepository.findByName(anyString())).thenThrow(DuplicateException.class);

        assertThrows(DuplicateException.class, () -> authorRepository.findByName(anyString()));
    }
}
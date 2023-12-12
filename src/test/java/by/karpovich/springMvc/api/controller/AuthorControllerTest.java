package by.karpovich.springMvc.api.controller;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.service.impl.AuthorServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration()
class AuthorControllerTest {

    private final Long AUTHOR_ID = 1L;
    @Mock
    private AuthorServiceImpl authorService;
    @InjectMocks
    private AuthorController authorController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController)
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    void save() throws Exception {
        doNothing().when(authorService).save(any(AuthorCreateDto.class));

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(generateAuthorCreateDto())))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        doNothing().when(authorService).update(any(AuthorCreateDto.class), any(Long.class));

        mockMvc.perform(put("/authors/{id}", AUTHOR_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(generateAuthorCreateDto())))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        when(authorService.deleteById(AUTHOR_ID)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/{id}", AUTHOR_ID))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        when(authorService.findById(AUTHOR_ID)).thenReturn(generateAuthorDto());

        mockMvc.perform(get("/authors/{id}", AUTHOR_ID))
                .andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        List<AuthorDto> authorList = Arrays.asList(generateAuthorDto(), generateAuthorDto2());

        when(authorService.findAll()).thenReturn(authorList);

        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk());
    }


    private AuthorDto generateAuthorDto() {
        List<SongDto> songs = new ArrayList<>();
        return new AuthorDto(1L, "Test", songs);
    }

    private AuthorDto generateAuthorDto2() {
        List<SongDto> songs = new ArrayList<>();
        return new AuthorDto(2L, "Test2", songs);
    }

    private AuthorCreateDto generateAuthorCreateDto() {
        return new AuthorCreateDto("Test");
    }
}
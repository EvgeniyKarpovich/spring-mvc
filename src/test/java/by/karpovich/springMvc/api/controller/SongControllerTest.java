package by.karpovich.springMvc.api.controller;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SongCreateDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.service.impl.SongServiceImpl;
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
class SongControllerTest {

    private final Long SONG_ID = 1L;
    @Mock
    private SongServiceImpl songService;
    @InjectMocks
    private SongController songController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(songController)
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    void save() throws Exception {
        doNothing().when(songService).save(any(SongCreateDto.class));

        mockMvc.perform(post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(generateSongCreateDto())))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        doNothing().when(songService).update(any(SongCreateDto.class), any(Long.class));

        mockMvc.perform(put("/songs/{id}", SONG_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(generateSongCreateDto())))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        when(songService.deleteById(SONG_ID)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/songs/{id}", SONG_ID))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        when(songService.findById(SONG_ID)).thenReturn(generateSongDto());

        mockMvc.perform(get("/songs/{id}", SONG_ID))
                .andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        List<SongDto> authorList = Arrays.asList(generateSongDto(), generateSongDto2());

        when(songService.findAll()).thenReturn(authorList);

        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk());
    }

    private SongCreateDto generateSongCreateDto() {
        List<Long> authorsId = Arrays.asList(1l, 2L);
        return new SongCreateDto("Test", SONG_ID, authorsId);
    }

    private SongDto generateSongDto() {
        List<AuthorCreateDto> authors = new ArrayList<>();
        return new SongDto(SONG_ID, "Test", authors, generateSingerDto());
    }

    private SongDto generateSongDto2() {
        List<AuthorCreateDto> authors = new ArrayList<>();
        return new SongDto(2L, "Test2", authors, generateSingerDto());
    }

    private SingerCreateDto generateSingerDto() {
        return new SingerCreateDto("Test");
    }
}
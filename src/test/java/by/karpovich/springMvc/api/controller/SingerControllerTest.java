package by.karpovich.springMvc.api.controller;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.service.impl.SingerServiceImpl;
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
class SingerControllerTest {

    private final Long SINGER_ID = 1L;
    @Mock
    private SingerServiceImpl singerService;
    @InjectMocks
    private SingerController singerController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(singerController)
                .build();
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Test
    void save() throws Exception {
        doNothing().when(singerService).save(any(SingerCreateDto.class));

        mockMvc.perform(post("/singers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(generateSingerCreatedDto())))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        doNothing().when(singerService).update(any(SingerCreateDto.class), any(Long.class));

        mockMvc.perform(put("/singers/{id}", SINGER_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(generateSingerCreatedDto())))
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        when(singerService.deleteById(SINGER_ID)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/singers/{id}", SINGER_ID))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        when(singerService.findById(SINGER_ID)).thenReturn(generateSingerDto());

        mockMvc.perform(get("/singers/{id}", SINGER_ID))
                .andExpect(status().isOk());
    }

    @Test
    void findAll() throws Exception {
        List<SingerDto> authorList = Arrays.asList(generateSingerDto(), generateSingerDto2());

        when(singerService.findAll()).thenReturn(authorList);

        mockMvc.perform(get("/singers"))
                .andExpect(status().isOk());
    }

    private SingerCreateDto generateSingerCreatedDto() {
        return new SingerCreateDto("Test");
    }


    private SingerDto generateSingerDto() {
        List<SongDto> songs = new ArrayList<>();
        return new SingerDto(1L, "Test", songs);
    }

    private SingerDto generateSingerDto2() {
        List<SongDto> songs = new ArrayList<>();
        return new SingerDto(2L, "Test", songs);
    }
}
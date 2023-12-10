package by.karpovich.springMvc.api.controller;

import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class SongController {

    private final SongServiceImpl songService;

    @Autowired
    public SongController(SongServiceImpl songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/songs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SongDto>> findBySingerId(@PathVariable("id") Long id) {
        List<SongDto> songsDto = songService.findBySingerId(id);

        return new ResponseEntity<>(songsDto, HttpStatus.OK);
    }
}

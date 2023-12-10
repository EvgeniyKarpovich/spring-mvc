package by.karpovich.springMvc.api.controller;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.api.dto.SongCreateDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongServiceImpl songService;

    @Autowired
    public SongController(SongServiceImpl songService) {
        this.songService = songService;
    }

//    @GetMapping(value = "/songs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<SongDto>> findBySingerId(@PathVariable("id") Long id) {
//        List<SongDto> songsDto = songService.findBySingerId(id);
//
//        return new ResponseEntity<>(songsDto, HttpStatus.OK);
//    }

    @PostMapping
    public void save(@RequestBody SongCreateDto songCreateDto) {
        songService.save(songCreateDto);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody SongCreateDto songCreateDto, @PathVariable("id") Long songId) {
        songService.update(songCreateDto, songId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long songId) {
        songService.deleteById(songId);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SongDto> findById(@PathVariable("id") Long songId) {
        SongDto dto = songService.findById(songId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SongDto>> findAll() {
        List<SongDto> songsDto = songService.findAll();

        return new ResponseEntity<>(songsDto, HttpStatus.OK);
    }
}

package by.karpovich.springMvc.api.controller;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.service.impl.SingerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/singers")
public class SingerController {

    private final SingerServiceImpl singerServiceImpl;

    @Autowired
    public SingerController(SingerServiceImpl singerServiceImpl) {
        this.singerServiceImpl = singerServiceImpl;
    }


    @PostMapping
    public void save(@RequestBody SingerCreateDto singer) {
        singerServiceImpl.save(singer);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody SingerCreateDto singer, @PathVariable("id") Long singerId) {
        singerServiceImpl.update(singer, singerId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long singerId) {
        singerServiceImpl.deleteById(singerId);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SingerDto> findById(@PathVariable("id") Long singerId) {
        SingerDto dto = singerServiceImpl.findById(singerId);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SingerDto>> findAll() {
        List<SingerDto> singersDto = singerServiceImpl.findAll();

        return new ResponseEntity<>(singersDto, HttpStatus.OK);
    }
}

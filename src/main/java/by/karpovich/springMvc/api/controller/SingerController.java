package by.karpovich.springMvc.api.controller;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.service.impl.SingerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SingerController {

    private final SingerServiceImpl singerServiceImpl;

    @Autowired
    public SingerController(SingerServiceImpl singerServiceImpl) {
        this.singerServiceImpl = singerServiceImpl;
    }


    @PostMapping("/addUser")
    public void saveUser(@ModelAttribute("user") SingerCreateDto singer) {
        singerServiceImpl.save(singer);
    }

    @GetMapping(value = "/singer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingerDto> userForm(@PathVariable("id") Long id) {
        SingerDto dto = singerServiceImpl.findById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/singers/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingerDto> findByName(@PathVariable("name") String name) {
        SingerDto dto = singerServiceImpl.findByName(name);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}

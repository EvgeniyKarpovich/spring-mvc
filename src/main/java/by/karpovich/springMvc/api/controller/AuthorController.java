package by.karpovich.springMvc.api.controller;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorServiceImpl authorServiceImpl;

    @Autowired
    public AuthorController(AuthorServiceImpl authorServiceImpl) {
        this.authorServiceImpl = authorServiceImpl;
    }

    @PostMapping("/save")
    public void save(@RequestBody AuthorCreateDto authorCreateDto) {
        authorServiceImpl.save(authorCreateDto);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody AuthorCreateDto authorCreateDto, @PathVariable("id") Long authorId) {
        authorServiceImpl.update(authorCreateDto, authorId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long authorId) {
        authorServiceImpl.deleteById(authorId);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable("id") Long authorId) {
        AuthorDto authorDto = authorServiceImpl.findById(authorId);

        return new ResponseEntity<>(authorDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDto>> findAll() {
        List<AuthorDto> singersDto = authorServiceImpl.findAll();

        return new ResponseEntity<>(singersDto, HttpStatus.OK);
    }
}

package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.AuthorMapper;
import by.karpovich.springMvc.mapper.SongMapper;
import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.repository.AuthorRepository;
import by.karpovich.springMvc.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper, SongRepository songRepository, SongMapper songMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    @Transactional
    public void save(AuthorCreateDto authorCreateDto) {
        validateAlreadyExists(authorCreateDto, null);

        Author authorEntity = authorMapper.mapFromDto(authorCreateDto);

        authorRepository.save(authorEntity);
    }

    @Transactional
    public void update(AuthorCreateDto authorCreateDto, Long authorId) {
        validateAlreadyExists(authorCreateDto, authorId);

        Author authorEntity = authorMapper.mapFromDto(authorCreateDto);
        authorEntity.setId(authorId);

        authorRepository.save(authorEntity);
    }

    @Transactional
    public boolean deleteById(Long authorId) {
        if (authorRepository.findById(authorId).isPresent()) {
            authorRepository.deleteById(authorId);
        }
        return false;
    }

    @Transactional
    public AuthorDto findById(Long authorId) {
        Author authorEntity = authorRepository.findById(authorId).orElseThrow(
                () -> new NotFoundEntityException(String.format("Author with id = %s not found", authorId)));

        AuthorDto authorDto = authorMapper.mapFromEntity(authorEntity);
        authorDto.setSongs(songMapper.mapListDtoFromListEntity(getSongs(authorEntity)));

        return authorDto;
    }

    private List<Song> getSongs(Author authorEntity) {
        List<Song> songs = new ArrayList<>();

        for (Song song : authorEntity.getSongs()) {
            Song songEntity = songRepository.findById(song.getId())
                    .orElseThrow(() -> new NotFoundEntityException(String.format("Song with id = %s  not found", song.getId())));
            songs.add(songEntity);
        }
        return songs;
    }

    @Transactional
    public List<AuthorDto> findAll() {
        List<Author> authorEntities = authorRepository.findAll();
        return authorMapper.mapListDtoFromListEntity(authorEntities);
    }

    @Transactional
    public Author findAuthorByIdWhichWillReturnModel(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(
                () -> new NotFoundEntityException(String.format("Author with id = %s not found", authorId)));
    }

    private void validateAlreadyExists(AuthorCreateDto authorCreateDto, Long authorId) {
        Optional<Author> authorEntity = authorRepository.findByName(authorCreateDto.name());
        if (authorEntity.isPresent() && !authorEntity.get().getId().equals(authorId)) {
            throw new DuplicateException(String.format("Author with name = %s already exist", authorCreateDto.name()));
        }
    }
}

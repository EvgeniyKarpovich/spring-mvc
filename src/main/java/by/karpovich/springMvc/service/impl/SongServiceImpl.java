package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.SongCreateDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.SongMapper;
import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl {
    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private final SingerServiceImpl singerService;
    private final AuthorServiceImpl authorService;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, SongMapper songMapper, SingerServiceImpl singerService, AuthorServiceImpl authorService) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
        this.singerService = singerService;
        this.authorService = authorService;
    }


    @Transactional
    public void save(SongCreateDto songCreateDto) {
        validateAlreadyExists(songCreateDto, null);

        Song songEntity = songMapper.mapFromDto(songCreateDto);
        songEntity.setSinger(singerService.findSingerByIdWhichWillReturnModel(songCreateDto.singerId()));
        songEntity.setAuthors(getAuthors(songCreateDto));
        songRepository.save(songEntity);
    }

    private List<Author> getAuthors(SongCreateDto songCreateDto) {
        List<Author> authors = new ArrayList<>();
        for (Long authorId : songCreateDto.authorsId()) {
            Author author = authorService.findAuthorByIdWhichWillReturnModel(authorId);
            authors.add(author);
        }
        return authors;
    }

    @Transactional
    public void update(SongCreateDto songCreateDto, Long songId) {
        validateAlreadyExists(songCreateDto, songId);

        Song songEntity = songMapper.mapFromDto(songCreateDto);
        songEntity.setId(songId);
        songEntity.setSinger(singerService.findSingerByIdWhichWillReturnModel(songCreateDto.singerId()));
        songEntity.setAuthors(getAuthors(songCreateDto));

        songRepository.save(songEntity);
    }

    @Transactional
    public boolean deleteById(Long songId) {
        if (songRepository.findById(songId).isPresent()) {
            songRepository.deleteById(songId);
        }
        return false;
    }

    @Transactional
    public SongDto findById(Long songId) {
        Song entity = songRepository.findById(songId).orElseThrow(
                () -> new NotFoundEntityException(String.format("Song with id = %s not found", songId)));
        return songMapper.mapFromEntity(entity);
    }

    @Transactional
    public List<SongDto> findAll() {
        List<Song> SongEntities = songRepository.findAll();
        return songMapper.mapListDtoFromListEntity(SongEntities);
    }

    @Transactional
    public Song findSongByIdWhichWillReturnModel(Long songId) {
        return songRepository.findById(songId).orElseThrow(
                () -> new NotFoundEntityException(String.format("Song with id = %s not found", songId)));
    }

    private void validateAlreadyExists(SongCreateDto songCreateDto, Long songId) {
        Optional<Song> songEntity = songRepository.findByName(songCreateDto.name());
        if (songEntity.isPresent() && !songEntity.get().getId().equals(songId)) {
            throw new DuplicateException(String.format("Song with name = %s already exist", songCreateDto.name()));
        }
    }
}

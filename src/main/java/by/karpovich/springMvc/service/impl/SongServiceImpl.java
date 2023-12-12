package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.SongCreateDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.SongMapper;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl {

    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Autowired
    public SongServiceImpl(SongRepository songRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    @Transactional
    public void save(SongCreateDto songCreateDto) {
        validateAlreadyExists(songCreateDto, null);

        Song songEntity = songMapper.mapFromDto(songCreateDto);
        songRepository.save(songEntity);
    }

    @Transactional
    public void update(SongCreateDto songCreateDto, Long songId) {
        validateAlreadyExists(songCreateDto, songId);

        Song songEntity = songMapper.mapFromDto(songCreateDto);
        songEntity.setId(songId);

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
    public Song findSongByIdWhichWillReturnModel(Long id) {
        return songRepository.findById(id).orElseThrow(
                () -> new NotFoundEntityException(String.format("Song with id = %s not found", id)));
    }


    private void validateAlreadyExists(SongCreateDto songCreateDto, Long id) {
        Optional<Song> songEntity = songRepository.findByName(songCreateDto.name());
        if (songEntity.isPresent() && !songEntity.get().getId().equals(id)) {
            throw new DuplicateException(String.format("Song with name = %s already exist", songCreateDto.name()));
        }
    }
}

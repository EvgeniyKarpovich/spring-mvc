package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.repository.impl.SongRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl {


    private final SongRepositoryImpl songRepository;

    @Autowired
    public SongServiceImpl(SongRepositoryImpl songRepository) {
        this.songRepository = songRepository;
    }

    @Transactional
    public List<SongDto> findBySingerId(Long singerId) {
        List<Song> singersEntity = songRepository.findBySingerId(singerId);
       return singersEntity.stream()
                .map(entity -> new SongDto(entity.getName()))
                .collect(Collectors.toList());
    }
}

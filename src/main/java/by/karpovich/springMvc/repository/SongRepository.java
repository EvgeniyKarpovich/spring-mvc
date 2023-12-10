package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.model.Song;

import java.util.Optional;

public interface SongRepository extends BaseRepository<Song, Long> {

    Optional<Song> findByName(String name);
}

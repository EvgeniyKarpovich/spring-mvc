package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.model.Singer;

import java.util.Optional;

public interface SingerRepository extends BaseRepository<Singer, Long> {

    Optional<Singer> findByName(String name);
}

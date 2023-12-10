package by.karpovich.springMvc.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, K> {

    void save(T t);

    void update(T t);
    void delete(K id);

    Optional<T> findById(K id);

    List<T> findAll();


}

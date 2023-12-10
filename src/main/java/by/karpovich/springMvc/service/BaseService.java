package by.karpovich.springMvc.service;

import java.util.List;

public interface BaseService<T, K> {
    void save(T t);

    void update(T t, K k);

    boolean deleteById(K id);

    T findById(K id);

    List<T> findAll();
}

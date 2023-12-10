package by.karpovich.springMvc.service;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.model.Singer;

import java.util.List;

public interface SingerService {

    void save(SingerCreateDto t);

    void update(SingerCreateDto t, Long k);

    boolean deleteById(Long id);

    SingerDto findById(Long id);

    List<SingerDto> findAll();

    SingerDto findByName(String singerName);
}

package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.SingerMapper;
import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.repository.impl.SingerRepositoryImpl;
import by.karpovich.springMvc.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SingerServiceImpl /*implements SingerService*/ {

    private final SingerRepositoryImpl singerRepository;
    private final SingerMapper singerMapper;

    @Autowired
    public SingerServiceImpl(SingerRepositoryImpl singerRepository, SingerMapper singerMapper) {
        this.singerRepository = singerRepository;
        this.singerMapper = singerMapper;
    }


    @Transactional
    public void save(SingerCreateDto singerCreateDto) {
        validateAlreadyExists(singerCreateDto, null);

        Singer singerEntity = singerMapper.mapFromDto(singerCreateDto);
        singerRepository.save(singerEntity);
    }

    //    @Override
    public void update(SingerCreateDto singer, Long aLong) {

    }

    //    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    //    @Override
    @Transactional
    public SingerDto findById(Long singerId) {
        Singer entity = singerRepository.findById(singerId).orElseThrow(
                () -> new NotFoundEntityException(String.format("Album with id = %s not found", singerId)));
        return singerMapper.mapFromEntity(entity);
    }

    //    @Override
    public List<SingerDto> findAll() {
        return null;
    }

    @Transactional
//    @Override
    public SingerDto findByName(String singerName) {
        Singer singer = singerRepository.findByName(singerName).get();
        return singerMapper.mapFromEntity(singer);
    }

    private void validateAlreadyExists(SingerCreateDto singerCreateDto, Long id) {
        Optional<Singer> singerEntity = singerRepository.findByName(singerCreateDto.name());
        if (singerEntity.isPresent() && !singerEntity.get().getId().equals(id)) {
            throw new DuplicateException(String.format("Singer with name = %s already exist", singerCreateDto.name()));
        }
    }
}

package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.SingerMapper;
import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SingerServiceImpl /*implements SingerService*/ {

    private final SingerRepository singerRepository;
    private final SingerMapper singerMapper;

    @Autowired
    public SingerServiceImpl(SingerRepository singerRepository, SingerMapper singerMapper) {
        this.singerRepository = singerRepository;
        this.singerMapper = singerMapper;
    }


    @Transactional
    public void save(SingerCreateDto singerCreateDto) {
        validateAlreadyExists(singerCreateDto, null);

        Singer singerEntity = singerMapper.mapFromDto(singerCreateDto);
        singerRepository.save(singerEntity);
    }

    @Transactional
    public void update(SingerCreateDto singerCreateDto, Long singerId) {
        validateAlreadyExists(singerCreateDto, singerId);

        Singer singerEntity = singerMapper.mapFromDto(singerCreateDto);
        singerEntity.setId(singerId);

        singerRepository.save(singerEntity);
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (singerRepository.findById(id).isPresent()) {
            singerRepository.deleteById(id);
        }
        return false;
    }

    @Transactional
    public SingerDto findById(Long singerId) {
        Singer entity = singerRepository.findById(singerId).orElseThrow(
                () -> new NotFoundEntityException(String.format("Singer with id = %s not found", singerId)));
        return singerMapper.mapFromEntity(entity);
    }

    @Transactional
    public List<SingerDto> findAll() {
        List<Singer> singerEntities = singerRepository.findAll();
        return singerMapper.mapListDtoFromListEntity(singerEntities);
    }

    @Transactional
    public Singer findSingerByIdWhichWillReturnModel(Long id) {
        return singerRepository.findById(id).orElseThrow(
                () -> new NotFoundEntityException(String.format("Singer with id = %s not found", id)));
    }

    private void validateAlreadyExists(SingerCreateDto singerCreateDto, Long id) {
        Optional<Singer> singerEntity = singerRepository.findByName(singerCreateDto.name());
        if (singerEntity.isPresent() && !singerEntity.get().getId().equals(id)) {
            throw new DuplicateException(String.format("Singer with name = %s already exist", singerCreateDto.name()));
        }
    }


}

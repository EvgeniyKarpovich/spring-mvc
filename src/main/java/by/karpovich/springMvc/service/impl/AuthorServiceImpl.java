package by.karpovich.springMvc.service.impl;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.exception.DuplicateException;
import by.karpovich.springMvc.exception.NotFoundEntityException;
import by.karpovich.springMvc.mapper.AuthorMapper;
import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Transactional
    public void save(AuthorCreateDto authorCreateDto) {
        validateAlreadyExists(authorCreateDto, null);

        Author authorEntity = authorMapper.mapFromDto(authorCreateDto);
        authorRepository.save(authorEntity);
    }

    @Transactional
    public void update(AuthorCreateDto authorCreateDto, Long authorId) {
        validateAlreadyExists(authorCreateDto, authorId);

        Author authorEntity = authorMapper.mapFromDto(authorCreateDto);
        authorEntity.setId(authorId);

        authorRepository.save(authorEntity);
    }

    @Transactional
    public boolean deleteById(Long authorId) {
        if (authorRepository.findById(authorId).isPresent()) {
            authorRepository.deleteById(authorId);
        }
        return false;
    }

    @Transactional
    public AuthorDto findById(Long authorId) {
        Author authorEntity = authorRepository.findById(authorId).orElseThrow(
                () -> new NotFoundEntityException(String.format("Author with id = %s not found", authorId)));
        return authorMapper.mapFromEntity(authorEntity);
    }

    @Transactional
    public List<AuthorDto> findAll() {
        List<Author> AuthorEntities = authorRepository.findAll();
        return authorMapper.mapListDtoFromListEntity(AuthorEntities);
    }

    @Transactional
    public Author findAuthorByIdWhichWillReturnModel(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(
                () -> new NotFoundEntityException(String.format("Author with id = %s not found", authorId)));
    }

    private void validateAlreadyExists(AuthorCreateDto authorCreateDto, Long authorId) {
        Optional<Author> authorEntity = authorRepository.findByName(authorCreateDto.name());
        if (authorEntity.isPresent() && !authorEntity.get().getId().equals(authorId)) {
            throw new DuplicateException(String.format("Author with name = %s already exist", authorCreateDto.name()));
        }
    }
}

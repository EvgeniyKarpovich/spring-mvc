package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    Author mapFromDto(AuthorCreateDto dto);

    @Mapping(target = "songs", ignore = true)
    AuthorDto mapFromEntity(Author entity);

    AuthorCreateDto mapCreateDtoFromEntity(Author entity);

    @Mapping(target = "songs", ignore = true)
    List<AuthorCreateDto> mapListCreateDtoFromEntity(List<Author> authorsEntity);

    @Mapping(target = "songs", ignore = true)
    List<AuthorDto> mapListDtoFromListEntity(List<Author> entities);
}
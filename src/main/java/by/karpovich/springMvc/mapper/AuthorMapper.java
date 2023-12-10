package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.model.Singer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = SongMapper.class)
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    Author mapFromDto(AuthorCreateDto dto);

    @Mapping(target = "songs", ignore = true)
    AuthorDto mapFromEntity(Author entity);


    AuthorCreateDto mapCreateDtoFromEntity(Author entity);
    List<AuthorCreateDto> mapListCreateDtoFromEntity(List<Author> authorsEntity);

    List<AuthorDto> mapListDtoFromListEntity(List<Author> entities);
}

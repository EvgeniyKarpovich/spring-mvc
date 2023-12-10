package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.AuthorCreateDto;
import by.karpovich.springMvc.api.dto.AuthorDto;
import by.karpovich.springMvc.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = SongMapper.class)
public abstract class AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    public abstract Author mapFromDto(AuthorCreateDto dto);

    public abstract AuthorDto mapFromEntity(Author entity);

    public abstract AuthorCreateDto mapCreateDtoFromEntity(Author entity);

    public abstract List<AuthorCreateDto> mapListCreateDtoFromEntity(List<Author> authorsEntity);

    public abstract List<AuthorDto> mapListDtoFromListEntity(List<Author> entities);
}

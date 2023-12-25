package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.SongCreateDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring", uses = {SingerMapper.class, AuthorMapper.class})
public interface SongMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "singer", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Song mapFromDto(SongCreateDto dto);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "singer", ignore = true)
    SongDto mapFromEntity(Song entity);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "singer", ignore = true)
    List<SongDto> mapListDtoFromListEntity(List<Song> entities);
}
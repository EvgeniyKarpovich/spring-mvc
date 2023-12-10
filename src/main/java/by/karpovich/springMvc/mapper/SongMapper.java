package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class SongMapper {

    public abstract SongDto mapFromEntity(Song entity);
    public abstract List<SongDto> mapFromEntity(List<Song> entities);
}

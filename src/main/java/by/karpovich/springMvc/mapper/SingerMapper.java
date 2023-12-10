package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.service.impl.SongServiceImpl;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = SongMapper.class)
public interface SingerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    Singer mapFromDto(SingerCreateDto dto);

    SingerDto mapFromEntity(Singer entity);

    List<SingerDto> mapListDtoFromListEntity(List<Singer> entities);
}

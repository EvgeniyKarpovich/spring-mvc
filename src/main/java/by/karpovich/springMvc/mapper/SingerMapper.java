package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.SingerCreateDto;
import by.karpovich.springMvc.api.dto.SingerDto;
import by.karpovich.springMvc.model.Singer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SingerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    Singer mapFromDto(SingerCreateDto dto);

    SingerDto mapFromEntity(Singer entity);

    SingerCreateDto mapSingerCreateDtoFromEntity(Singer entity);

    List<SingerDto> mapListDtoFromListEntity(List<Singer> entities);
}

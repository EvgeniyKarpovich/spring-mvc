package by.karpovich.springMvc.mapper;

import by.karpovich.springMvc.api.dto.SongCreateDto;
import by.karpovich.springMvc.api.dto.SongDto;
import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.service.impl.SingerServiceImpl;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {/*SingerMapper.class,*/ AuthorMapper.class})
public abstract class SongMapper {


    @Autowired
    private SingerServiceImpl singerService;


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "singer", ignore = true)
    @Mapping(target = "authors", ignore = true)
    public abstract Song mapFromDto(SongCreateDto dto);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "singer", ignore = true)
    public abstract SongDto mapFromEntity(Song entity);

    public abstract List<SongDto> mapListDtoFromListEntity(List<Song> entities);

//    @AfterMapping
//    protected void setSinger(@MappingTarget Song songEntity, SongCreateDto songCreateDto) {
//        Singer singerEntity = singerService.findSingerByIdWhichWillReturnModel(songCreateDto.singerId());
//
//        songEntity.setSinger(singerEntity);
//    }
}

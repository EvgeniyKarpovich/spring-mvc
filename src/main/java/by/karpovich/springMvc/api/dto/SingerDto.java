package by.karpovich.springMvc.api.dto;

import java.util.List;

public record SingerDto(Long id,
                        String name,
                        List<SongDto> songs) {
}

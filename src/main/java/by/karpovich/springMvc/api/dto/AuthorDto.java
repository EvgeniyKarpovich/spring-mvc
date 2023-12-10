package by.karpovich.springMvc.api.dto;

import java.util.List;

public record AuthorDto(Long id,
                        String name,
                        List<SongDto> songs) {
}

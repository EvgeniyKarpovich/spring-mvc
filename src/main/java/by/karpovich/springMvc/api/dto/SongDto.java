package by.karpovich.springMvc.api.dto;

import java.util.List;

public record SongDto(Long id,
                      String name,
                      List<AuthorCreateDto> authors,
                      SingerCreateDto singer) {
}

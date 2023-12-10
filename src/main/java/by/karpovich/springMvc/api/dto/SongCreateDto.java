package by.karpovich.springMvc.api.dto;

import java.util.List;

public record SongCreateDto(String name,
                            Long singerId,
                            List<Long> authorsId) {
}

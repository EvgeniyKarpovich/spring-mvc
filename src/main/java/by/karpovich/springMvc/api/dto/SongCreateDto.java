package by.karpovich.springMvc.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SongCreateDto(
        @NotBlank(message = "name must not be null")
        String name,
        @NotNull(message = "name must not be null")
        Long singerId,
        List<Long> authorsId) {
}

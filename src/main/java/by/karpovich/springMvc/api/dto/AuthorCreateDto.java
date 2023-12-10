package by.karpovich.springMvc.api.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorCreateDto(
        @NotBlank(message = "name must not be null")
        String name) {
}

package by.karpovich.springMvc.api.dto;

import java.util.List;

public class AuthorDto {

   private Long id;
   private String name;
   private List<SongDto> songs;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorDto(Long id, String name, List<SongDto> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }
}

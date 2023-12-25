package by.karpovich.springMvc.api.dto;

import java.util.List;

public class SongDto {

   private Long id;
   private String name;
   private List<AuthorCreateDto> authors;
   private SingerCreateDto singer;

    public SongDto() {
    }

    public SongDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SongDto(Long id, String name, List<AuthorCreateDto> authors) {
        this.id = id;
        this.name = name;
        this.authors = authors;
    }

    public SongDto(Long id, String name, List<AuthorCreateDto> authors, SingerCreateDto singer) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.singer = singer;
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

    public List<AuthorCreateDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorCreateDto> authors) {
        this.authors = authors;
    }

    public SingerCreateDto getSinger() {
        return singer;
    }

    public void setSinger(SingerCreateDto singer) {
        this.singer = singer;
    }
}

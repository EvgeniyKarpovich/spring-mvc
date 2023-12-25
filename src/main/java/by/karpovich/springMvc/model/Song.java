package by.karpovich.springMvc.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "songs_author",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    public Song() {
    }

    public Song(Long id, String name, Singer singer) {
        super(id, name);
        this.singer = singer;
    }

    public Song(String name, Singer singer, List<Author> authors) {
        super(name);
        this.singer = singer;
        this.authors = authors;
    }

    public Song(Long id, String name) {
        super(id, name);
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Song(Long id, String name, Singer singer, List<Author> authors) {
        super(id, name);
        this.singer = singer;
        this.authors = authors;
    }
}

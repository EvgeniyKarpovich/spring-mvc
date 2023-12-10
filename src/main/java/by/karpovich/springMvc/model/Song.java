package by.karpovich.springMvc.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @ManyToMany
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

//    public Song(Long id, String name, Singer singer, List<Author> authors) {
//        super(id, name);
//        this.singer = singer;
//        this.authors = authors;
//    }
//
//    public Song(Singer singer) {
//        this.singer = singer;
//    }
//
//    public Song(List<Author> authors) {
//        this.authors = authors;
//    }
//
//    public Song(Singer singer, List<Author> authors) {
//        this.singer = singer;
//        this.authors = authors;
//    }
}

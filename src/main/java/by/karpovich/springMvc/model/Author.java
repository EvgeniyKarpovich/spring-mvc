package by.karpovich.springMvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Song> songs = new ArrayList<>();

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Author() {
    }

    public Author(String name) {
        super(name);
    }

    public Author(Long id, String name) {
        super(id, name);
    }

    public Author(List<Song> songs) {
        this.songs = songs;
    }


    public Author(Long id, List<Song> songs) {
        super(id);
        this.songs = songs;
    }

    public Author(Long id, String name, List<Song> songs) {
        super(id, name);
        this.songs = songs;
    }
}

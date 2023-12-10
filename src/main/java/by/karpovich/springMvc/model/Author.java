package by.karpovich.springMvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    @ManyToMany(mappedBy = "authors")
    private List<Song> songs = new ArrayList<>();

    public Author() {
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

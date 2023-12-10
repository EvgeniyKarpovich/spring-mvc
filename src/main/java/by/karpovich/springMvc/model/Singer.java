package by.karpovich.springMvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "singers")
public class Singer extends BaseEntity {
    @OneToMany(mappedBy = "singer", fetch = FetchType.LAZY)
    private List<Song> songs = new ArrayList<>();

    public Singer() {
    }

    public Singer(Long id, String name) {
        super(id, name);
    }

    public Singer(Long id, List<Song> songs) {
        super(id);
        this.songs = songs;
    }

    public Singer(Long id, String name, List<Song> songs) {
        super(id, name);
        this.songs = songs;
    }

    public Singer(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}

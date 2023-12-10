package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("""
            select s from Song s where s.name = :songName
            """)
    Optional<Song> findByName(@Param("songName") String songName);

//    public List<Song> findBySingerId(Long singerId) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("select s from Song s where s.singer.id = :singerId", Song.class)
//                .setParameter("singerId", singerId)
//                .getResultList();
//    }
}

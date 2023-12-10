package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {

    @Query("""
            select s from Singer s where s.name = :singerName
            """)
    Optional<Singer> findByName(@Param("singerName") String singerName);
}

package by.karpovich.springMvc.repository;

import by.karpovich.springMvc.model.Author;
import by.karpovich.springMvc.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("""
            select a from Author a where a.name = :authorName
            """)
    Optional<Author> findByName(@Param("authorName") String authorName);
}

package by.karpovich.springMvc.repository.impl;

import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.model.Song;
import by.karpovich.springMvc.repository.SongRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepositoryImpl implements SongRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public SongRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Song song) {
        sessionFactory.getCurrentSession().save(song);
    }

    @Override
    public void update(Song song) {
        sessionFactory.getCurrentSession().saveOrUpdate(song);
    }

    @Override
    public void delete(Long songId) {
        Song song = sessionFactory.getCurrentSession().find(Song.class, songId);
        sessionFactory.getCurrentSession().delete(song);
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(Song.class, songId));
    }

    @Override
    public List<Song> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select s from Song s", Song.class)
                .getResultList();
    }

    @Override
    public Optional<Song> findByName(String songName) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("select s from Singer s where s.name = :songName", Song.class)
                .setParameter("songName", songName)
                .getSingleResult());
    }

    public List<Song> findBySingerId(Long singerId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select s from Song s where s.singer.id = :singerId", Song.class)
                .setParameter("singerId", singerId)
                .getResultList();
    }
}

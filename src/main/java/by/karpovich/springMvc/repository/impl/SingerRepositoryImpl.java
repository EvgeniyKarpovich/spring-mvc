package by.karpovich.springMvc.repository.impl;

import by.karpovich.springMvc.model.Singer;
import by.karpovich.springMvc.repository.SingerRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SingerRepositoryImpl implements SingerRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public SingerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Singer singer) {
        sessionFactory.getCurrentSession().save(singer);
    }

    @Override
    public void update(Singer singer) {
        sessionFactory.getCurrentSession().saveOrUpdate(singer);
    }

    @Override
    public void delete(Long singerId) {
        Singer singer = sessionFactory.getCurrentSession().find(Singer.class, singerId);
        sessionFactory.getCurrentSession().delete(singer);
    }

    @Override
    public Optional<Singer> findById(Long singerId) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(Singer.class, singerId));
    }

    @Override
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("select s from Singer s", Singer.class)
                .getResultList();
    }

    @Override
    public Optional<Singer> findByName(String singerName) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("select s from Singer s where s.name = :singerName", Singer.class)
                .setParameter("singerName", singerName)
                .getSingleResult());
    }
}

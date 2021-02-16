package com.dev.spring.dao.impl;

import com.dev.spring.dao.MovieSessionDao;
import com.dev.spring.exception.DataProcessingException;
import com.dev.spring.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not insert movie session: "
                    + movieSession.getMovie(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<MovieSession> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(MovieSession.class);
            criteriaQuery.from(MovieSession.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error occurred while retrieving movie sessions", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT ms FROM MovieSession ms "
                            + "WHERE ms.id = :id "
                            + "AND ms.showTime >= :timeFrom "
                            + "AND ms.showTime <= :timeTo", MovieSession.class)
                    .setParameter("id", movieId)
                    .setParameter("timeFrom", date.atStartOfDay())
                    .setParameter("timeTo", date.atTime(LocalTime.MAX))
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can not obtain movie sessions for movie with id: "
                    + movieId + " on date: " + date, e);
        }
    }

    @Override
    public MovieSession update(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not update movie session: "
                    + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            MovieSession movieSession = session.load(MovieSession.class, id);
            session.remove(movieSession);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not delete movie session with id: "
                    + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<MovieSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(MovieSession.class, id));
        } catch (Exception e) {
            throw new RuntimeException("Can not get movie session with id: " + id, e);
        }
    }
}

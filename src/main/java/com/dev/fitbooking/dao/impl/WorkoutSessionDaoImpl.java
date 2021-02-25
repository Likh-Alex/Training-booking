package com.dev.fitbooking.dao.impl;

import com.dev.fitbooking.dao.WorkoutSessionDao;
import com.dev.fitbooking.exception.DataProcessingException;
import com.dev.fitbooking.model.WorkoutSession;
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
public class WorkoutSessionDaoImpl implements WorkoutSessionDao {
    private final SessionFactory sessionFactory;

    public WorkoutSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public WorkoutSession add(WorkoutSession workoutSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(workoutSession);
            transaction.commit();
            return workoutSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not insert workout session: "
                    + workoutSession.getWorkout(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<WorkoutSession> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<WorkoutSession> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(WorkoutSession.class);
            criteriaQuery.from(WorkoutSession.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error occurred while retrieving workout sessions", e);
        }
    }

    @Override
    public List<WorkoutSession> findAvailableSessions(Long workoutId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT ms FROM WorkoutSession ms "
                            + "WHERE ms.id = :id "
                            + "AND ms.workoutTime >= :timeFrom "
                            + "AND ms.workoutTime <= :timeTo", WorkoutSession.class)
                    .setParameter("id", workoutId)
                    .setParameter("timeFrom", date.atStartOfDay())
                    .setParameter("timeTo", date.atTime(LocalTime.MAX))
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can not obtain workout sessions for workout with id: "
                    + workoutId + " on date: " + date, e);
        }
    }

    @Override
    public WorkoutSession update(WorkoutSession workoutSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(workoutSession);
            transaction.commit();
            return workoutSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not update workout session: "
                    + workoutSession, e);
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
            WorkoutSession workoutSession = session.load(WorkoutSession.class, id);
            session.remove(workoutSession);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not delete workout session with id: "
                    + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<WorkoutSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(WorkoutSession.class, id));
        } catch (Exception e) {
            throw new RuntimeException("Can not get workout session with id: " + id, e);
        }
    }
}

package com.dev.fitbooking.dao.impl;

import com.dev.fitbooking.dao.WorkoutDao;
import com.dev.fitbooking.exception.DataProcessingException;
import com.dev.fitbooking.model.Workout;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class WorkoutDaoImpl implements WorkoutDao {
    private final SessionFactory sessionFactory;

    public WorkoutDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Workout add(Workout workout) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(workout);
            transaction.commit();
            return workout;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not insert workout: " + workout, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Workout> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Workout.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can not get workout by id: " + id, e);
        }
    }

    @Override
    public List<Workout> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Workout> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Workout.class);
            criteriaQuery.from(Workout.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error occurred while retrieving workouts", e);
        }
    }
}

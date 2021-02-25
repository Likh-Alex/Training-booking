package com.dev.fitbooking.dao.impl;

import com.dev.fitbooking.dao.FitnessRoomDao;
import com.dev.fitbooking.exception.DataProcessingException;
import com.dev.fitbooking.model.FitnessRoom;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class FitnessRoomDaoImpl implements FitnessRoomDao {
    private final SessionFactory sessionFactory;

    public FitnessRoomDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public FitnessRoom add(FitnessRoom fitnessRoom) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(fitnessRoom);
            transaction.commit();
            return fitnessRoom;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not insert fitness room "
                    + fitnessRoom.getDescription(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<FitnessRoom> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(FitnessRoom.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can not get fitness room by id: " + id, e);
        }
    }

    @Override
    public List<FitnessRoom> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<FitnessRoom> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(FitnessRoom.class);
            criteriaQuery.from(FitnessRoom.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error occurred while retrieving fitness room", e);
        }
    }
}

package com.dev.fitbooking.dao.impl;

import com.dev.fitbooking.dao.RoleDao;
import com.dev.fitbooking.exception.DataProcessingException;
import com.dev.fitbooking.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can not insert role: " + role, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session
                    .createQuery("SELECT r FROM Role r "
                            + "WHERE r.roleName = :role_name", Role.class)
                    .setParameter("role_name", Role.RoleName.valueOf(roleName))
                    .getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can not get role by role name: " + roleName, e);
        }
    }
}

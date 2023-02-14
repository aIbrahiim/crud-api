package com.example.techassessment.dao;

import com.example.techassessment.model.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BranchDaoImpl implements BranchDao {
    @Override
    public Object addBranch(Branch branch) {
        Transaction transaction = null;
        Object result = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.save(branch);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Branch fetchById(String id) {
        Transaction transaction = null;
        Object result = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.get(Branch.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return (Branch) result;
    }

    @Override
    public List<Branch> fetchAll(int page, int size) {
        Transaction transaction = null;
        String hqlQuery = "FROM Branch";
        List result = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hqlQuery);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
            result = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Branch update(Branch branch) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(branch);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return branch;
    }

    @Override
    public void delete(String id) {
        Transaction transaction = null;
        String hqlQuery = "DELETE FROM Branch e WHERE e.id = :id";
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hqlQuery);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

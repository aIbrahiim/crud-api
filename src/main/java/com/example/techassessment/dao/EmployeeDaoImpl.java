package com.example.techassessment.dao;

import com.example.techassessment.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public Object addEmployee(Employee employee) {
        Transaction transaction = null;
        Object result = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.save(employee);
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
    public Boolean existsByNationalId(String nationalId) {
        Transaction transaction = null;
        Object result = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select 1 from Employee e where e.nationalId = :nationalId");
            query.setParameter("nationalId", nationalId );
            result = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result == null;
    }

    @Override
    public Employee fetchById(String id) {
        Transaction transaction = null;
        Object result = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.get(Employee.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return (Employee) result;
    }

    @Override
    public List<Employee> fetchAll(int page, int size) {
        Transaction transaction = null;
        String hqlQuery = "FROM Employee";
        List result = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hqlQuery);
            query.setFirstResult((page-1) * size);
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
    public Employee update(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void delete(String id) {
        Transaction transaction = null;
        String hqlQuery = "DELETE FROM Employee e WHERE e.id = :id";
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

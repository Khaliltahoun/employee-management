package com.example.dao;

import com.example.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

@Named
@ApplicationScoped
public class EmployeeDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");

    public void save(Employee employee) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            // If the employee has no ID yet, it's a new record -> persist
            // Otherwise, use merge to update the existing record
            if (employee.getId() == null) {
                entityManager.persist(employee);
            } else {
                entityManager.merge(employee);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde de l'employé", e);
        } finally {
            entityManager.close();
        }
    }

    public Employee findById(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.find(Employee.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Employee> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void delete(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Employee emp = entityManager.find(Employee.class, id);
            if (emp != null) {
                entityManager.remove(emp);
            }
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}

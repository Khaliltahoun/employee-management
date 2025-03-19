package com.example.dao;

import com.example.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.util.List;

@Named
@ApplicationScoped
public class EmployeeDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
    private EntityManager entityManager = emf.createEntityManager();

    public void save(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public void delete(Long id) {
        Employee emp = findById(id);
        if (emp != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(emp);
            entityManager.getTransaction().commit();
        }
    }
}

package com.example.dao;

import com.example.model.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Named
@ApplicationScoped
public class ProjectDAO {

    @PersistenceContext
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");
    private EntityManager entityManager = emf.createEntityManager();

    public void save(Project project) {
        entityManager.persist(project);
    }

    public Project findById(Long id) {
        return entityManager.find(Project.class, id);
    }

    public List<Project> findAll() {
        return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    public void delete(Long id) {
        Project project = findById(id);
        if (project != null) {
            entityManager.remove(project);
        }
    }
}

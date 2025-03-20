package com.example.dao;

import com.example.model.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

@Named
@ApplicationScoped
public class ProjectDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeePU");

    public void save(Project project) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            // If the project is new (id == null), persist it;
            // otherwise, merge to update the existing record.
            if (project.getId() == null) {
                entityManager.persist(project);
            } else {
                entityManager.merge(project);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde du projet", e);
        } finally {
            entityManager.close();
        }
    }

    public Project findById(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.find(Project.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Project> findAll() {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.createQuery("SELECT p FROM Project p", Project.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void delete(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Project project = entityManager.find(Project.class, id);
            if (project != null) {
                entityManager.remove(project);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression du projet", e);
        } finally {
            entityManager.close();
        }
    }
}

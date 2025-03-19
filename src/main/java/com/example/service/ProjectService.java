package com.example.service;

import com.example.dao.ProjectDAO;
import com.example.model.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class ProjectService {

    @Inject
    private ProjectDAO projectDAO;

    public void save(Project project) {
        projectDAO.save(project);
    }

    public Project findById(Long id) {
        return projectDAO.findById(id);
    }

    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    public void delete(Long id) {
        projectDAO.delete(id);
    }
}

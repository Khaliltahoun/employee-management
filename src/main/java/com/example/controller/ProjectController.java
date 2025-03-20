package com.example.controller;

import com.example.model.Project;
import com.example.service.ProjectService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped // Use @ViewScoped if a shorter lifecycle is desired.
public class ProjectController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ProjectService projectService;

    private Project newProject;
    private Project selectedProject;

    @PostConstruct
    public void init() {
        newProject = new Project();
    }

    /**
     * Adds a project and resets the newProject.
     * @return redirection URL
     */
    public String addProject() {
        if (newProject != null) {
            projectService.save(newProject);
            System.out.println("Projet ajouté : " + newProject);
            newProject = new Project(); // Reset after saving
        }
        return "project-list.xhtml?faces-redirect=true";
    }

    /**
     * Returns the list of all projects.
     */
    public List<Project> getAllProjects() {
        return projectService.findAll();
    }

    /**
     * Deletes a project by ID.
     */
    public void delete(Long id) {
        projectService.delete(id);
    }

    // Getters and Setters
    public Project getNewProject() {
        return newProject;
    }

    public void setNewProject(Project newProject) {
        this.newProject = newProject;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }
}

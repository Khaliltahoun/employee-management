package com.example.controller;

import com.example.model.Project;
import com.example.service.ProjectService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ProjectController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ProjectService projectService;
    private Project newProject = new Project();
    private List<Project> allProjects = new ArrayList<>();

    private Project project = new Project();
    private Project selectedProject; // Ajout de la propriété selectedProject



    @PostConstruct
    public void init() {
        newProject = new Project();
    }

    public String addProject() {
        if (newProject != null) {
            projectService.save(newProject); // Sauvegarde dans la base de données
            System.out.println("Projet ajouté : " + newProject);
        }
        return "project-list.xhtml?faces-redirect=true";
    }

    public Project getNewProject() {
        return newProject;
    }

    public void setNewProject(Project newProject) {
        this.newProject = newProject;
    }

    public void save() {
        projectService.save(project);
        project = new Project(); // Réinitialisation après sauvegarde
    }

    public List<Project> getAllProjects() {
        return projectService.findAll();
    }

    public void delete(Long id) {
        projectService.delete(id);
    }

    // Getters et Setters
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }
}

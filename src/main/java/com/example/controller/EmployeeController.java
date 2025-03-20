package com.example.controller;

import com.example.model.Employee;
import com.example.model.Post;
import com.example.model.Project;
import com.example.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped // You may change this to @ViewScoped for a shorter lifecycle if preferred.
public class EmployeeController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());

    @Inject
    private EmployeeService employeeService;

    // Inject ProjectController to get the selected project.
    @Inject
    private ProjectController projectController;

    private Employee newEmployee;
    private Employee selectedEmployee;
    private String implication; // Extra field if needed

    @PostConstruct
    public void init() {
        newEmployee = new Employee();
        // Leave the projects list empty; it will be automatically initialized (see entity)
    }

    /**
     * Adds a new employee to the database.
     * The employee is created without any projects assigned.
     */
    public String addEmployee() {
        if (newEmployee != null) {
            try {
                employeeService.save(newEmployee);
                LOGGER.log(Level.INFO, "Employé ajouté avec succès : {0}", newEmployee);
                // Reinitialize newEmployee for next creation (projects list will be empty)
                newEmployee = new Employee();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Erreur lors de l'ajout de l'employé", e);
                return null;
            }
        }
        return "employee-list.xhtml?faces-redirect=true";
    }

    /**
     * Assigns (adds) a selected project from ProjectController to the selected employee.
     * This method adds the project to the employee's projects list without overwriting existing assignments.
     */
    public String affecterProjet() {
        if (selectedEmployee != null && projectController.getSelectedProject() != null) {
            Project selectedProject = projectController.getSelectedProject();
            // Add the project if not already assigned
            if (!selectedEmployee.getProjects().contains(selectedProject)) {
                selectedEmployee.getProjects().add(selectedProject);
            } else {
                LOGGER.log(Level.INFO, "Le projet est déjà assigné à l'employé : {0}", selectedEmployee);
            }
            try {
                // Update (merge) the employee with the new project assignment.
                employeeService.save(selectedEmployee);
                LOGGER.log(Level.INFO, "Projet ajouté à l'employé : {0}", selectedEmployee);
                return "employee-list.xhtml?faces-redirect=true";
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Erreur lors de l'affectation du projet", e);
                return null;
            }
        } else {
            LOGGER.log(Level.WARNING, "Aucun employé ou projet sélectionné pour affectation.");
            return null;
        }
    }

    /**
     * Returns a list of all employees.
     */
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    /**
     * Deletes an employee by its ID.
     */
    public void delete(Long id) {
        try {
            employeeService.delete(id);
            LOGGER.log(Level.INFO, "Employé supprimé avec l'ID : {0}", id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur lors de la suppression de l'employé", e);
        }
    }

    /**
     * Returns a list of available Post values.
     */
    public List<Post> getPostValues() {
        return Arrays.asList(Post.values());
    }

    // Getters and Setters
    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }
}

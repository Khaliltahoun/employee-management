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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class EmployeeController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmployeeService employeeService;

    @PostConstruct
    public void init() {
        newEmployee = new Employee();
        newEmployee.setProject(new Project()); // Évite l'erreur null sur project
    }

    private Employee employee = new Employee();
    private Employee selectedEmployee; // Ajout de selectedEmployee

    public void save() {
        employeeService.save(employee);
        employee = new Employee(); // Réinitialisation après sauvegarde
    }

    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    public void delete(Long id) {
        employeeService.delete(id);
    }

    public List<Post> getPostValues() {
        return Arrays.asList(Post.values());
    }


    // Getter et Setter pour Employee
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Getter et Setter pour SelectedEmployee
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
    private String implication;  // Déclaration de la propriété

    public String getImplication() {
        return implication;
    }

    public void setImplication(String implication) {
        this.implication = implication;
    }

    private Employee newEmployee = new Employee();
    private List<Employee> allEmployees = new ArrayList<>();





    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }
    public String addEmployee() {
        // Sauvegarde dans la base de données (à implémenter)
        System.out.println("Employé ajouté : " + newEmployee);
        return "employee-list.xhtml?faces-redirect=true";
    }
}

package com.example.service;

import com.example.dao.EmployeeDAO;
import com.example.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class EmployeeService {

    @Inject
    private EmployeeDAO employeeDAO;

    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    public Employee findById(Long id) {
        return employeeDAO.findById(id);
    }

    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    public void delete(Long id) {
        employeeDAO.delete(id);
    }
    public void saveEmployee(Employee employee) {
        save(employee); // Calls the existing save method
    }

    public List<Employee> getAllEmployees() {
        return findAll(); // Calls the existing findAll method
    }

}

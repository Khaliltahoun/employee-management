package com.example.converter;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "employeeConverter", managed = true)

public class EmployeeConverter implements Converter<Employee> {

    @Inject
    private EmployeeService employeeService;

    @Override
    public Employee getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            return employeeService.findById(id);  // Look up the Employee by its ID
        } catch (NumberFormatException e) {
            return null; // Avoid conversion errors
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Employee employee) {
        if (employee == null || employee.getId() == null) {
            return "";
        }
        return String.valueOf(employee.getId()); // Return the ID as a String
    }
}

package com.example.converter;

import com.example.model.Project;
import com.example.service.ProjectService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;


@FacesConverter(value = "projectConverter", managed = true)

public class ProjectConverter implements Converter<Project> {

    @Inject
    private ProjectService projectService;

    @Override
    public Project getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            Long id = Long.parseLong(value);
            return projectService.findById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Project project) {
        if (project == null || project.getId() == null) {
            return "";
        }
        return String.valueOf(project.getId());
    }
}

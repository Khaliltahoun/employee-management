package com.example.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@FacesConverter(value = "skillsConverter")
public class SkillsConverter implements Converter<Object> {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return List.of();
        }
        return Arrays.stream(value.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof List<?> list) {
            return String.join(",", list.stream()
                    .map(Object::toString)
                    .collect(Collectors.toList()));
        }
        return "";
    }
}

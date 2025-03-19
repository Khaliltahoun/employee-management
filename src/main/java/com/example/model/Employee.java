package com.example.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @ElementCollection
    private List<String> skills;

    @ManyToOne
    @JoinColumn(name = "project_id") // Explicitly naming the foreign key column
    private Project project;

    @Enumerated(EnumType.STRING)
    private Post post;

    // Constructors
    public Employee() {}

    public Employee(String name, String email, List<String> skills, Project project, Post post) {
        this.name = name;
        this.email = email;
        this.skills = skills;
        this.project = project;
        this.post = post;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
}

package xyz.bexbe.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "abr_name")
    private String abrName;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "owner")
    private Project ownedProject;

    @OneToMany(mappedBy = "assignee")
    private List<Issue> issues;

    @ManyToMany(mappedBy = "users")
    private List<Project> projects;
}

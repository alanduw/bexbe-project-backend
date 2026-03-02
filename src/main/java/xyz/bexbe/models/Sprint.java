package xyz.bexbe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "sprints")
public class Sprint {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private String start;

    @Column(name = "end_date")
    private String end;

    @Column(name = "goal")
    private String goal;

    @OneToMany(mappedBy = "sprint", fetch = FetchType.EAGER)
    private List<Issue> issues;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}

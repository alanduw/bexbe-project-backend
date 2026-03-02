package xyz.bexbe.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
//@AllArgsConstructor
@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "priority")
    private String priority;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @Column(name = "points")
    private Long points;

    @Column(name = "created")
    private String created;

    @Column(name = "tags")
    private String tags;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}

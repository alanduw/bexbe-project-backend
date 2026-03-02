package xyz.bexbe.dtos;

import jakarta.persistence.*;
import lombok.Data;
import xyz.bexbe.models.Sprint;
import xyz.bexbe.models.User;

@Data
public class IssueDto {
    private Long id;

    private String title;

    private String type;

    private String priority;

    private String status;

    private Long assignee;

    private Long sprint;

    private Long points;

    private String created;

    private String tags;
}

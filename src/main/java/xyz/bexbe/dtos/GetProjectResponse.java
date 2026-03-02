package xyz.bexbe.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GetProjectResponse {
    private List<SprintDto> sprints;
    private List<IssueDto> issues;
    private List<UserDto> users;
    private UserDto owner;
}

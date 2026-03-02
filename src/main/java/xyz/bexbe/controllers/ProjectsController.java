package xyz.bexbe.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bexbe.dtos.*;
import xyz.bexbe.models.Issue;
import xyz.bexbe.models.Project;
import xyz.bexbe.models.Sprint;
import xyz.bexbe.models.User;
import xyz.bexbe.services.DemoSeedService;
import xyz.bexbe.services.IssuesService;
import xyz.bexbe.services.ProjectsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private IssuesService issuesService;

    @Autowired
    private DemoSeedService demoSeedService;

    @PostMapping
    public String createProject(@RequestBody CreateProjectRequest createProjectRequest) throws JsonProcessingException {
        Project createdProject = projectsService.createProject(createProjectRequest);

        CreateProjectResponse createProjectResponse = new CreateProjectResponse();
        createProjectResponse.setAccessToken(createdProject.getAccessToken());

        UserDto userDto = new UserDto();
        userDto.setId(createdProject.getOwner().getId());
        userDto.setFirstName(createdProject.getOwner().getFirstName());
        userDto.setLastName(createdProject.getOwner().getLastName());
        userDto.setAbrName(createdProject.getOwner().getAbrName());
        userDto.setRole(createdProject.getOwner().getRole());

        createProjectResponse.setOwner(userDto);

        if (createProjectRequest.isUseSeed()) {
            demoSeedService.createFromDemoSeed(createdProject);
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(createProjectResponse);
    }

    @PostMapping("users")
    public String addUserToProject(@RequestBody AddUserToProjectRequest addUserToProjectRequest) throws JsonProcessingException {
        User addedUser = projectsService.addUserToProject(addUserToProjectRequest);

        UserDto userDto = new UserDto();
        userDto.setId(addedUser.getId());
        userDto.setFirstName(addedUser.getFirstName());
        userDto.setLastName(addedUser.getLastName());
        userDto.setAbrName(addedUser.getAbrName());
        userDto.setRole(addedUser.getRole());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userDto);
    }

    @GetMapping
    public String getProject(@RequestParam String accessToken) throws JsonProcessingException {
        Project retrievedProject = projectsService.getProjectByAccessToken(accessToken);

        GetProjectResponse getProjectResponse = new GetProjectResponse();

        UserDto owner = new UserDto();
        owner.setId(retrievedProject.getOwner().getId());
        owner.setFirstName(retrievedProject.getOwner().getFirstName());
        owner.setLastName(retrievedProject.getOwner().getLastName());
        owner.setAbrName(retrievedProject.getOwner().getAbrName());
        owner.setRole(retrievedProject.getOwner().getRole());

        getProjectResponse.setOwner(owner);

        getProjectResponse.setUsers(new ArrayList<>());
        if (retrievedProject.getUsers() != null) {
            getProjectResponse.getUsers().addAll(
                    retrievedProject.getUsers().stream().map(user -> {
                        UserDto userDto = new UserDto();
                        userDto.setId(user.getId());
                        userDto.setFirstName(user.getFirstName());
                        userDto.setLastName(user.getLastName());
                        userDto.setAbrName(user.getAbrName());
                        userDto.setRole(user.getRole());

                        return userDto;
                    }).toList()
            );
        }

        List<IssueDto> issues;
        if (retrievedProject.getSprints() != null) {
            issues = retrievedProject.getSprints()
                    .stream()
                    .map(Sprint::getIssues)
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .map(issue -> {
                        IssueDto issueDto = new IssueDto();
                        issueDto.setId(issue.getId());
                        issueDto.setAssignee(issue.getAssignee().getId());
                        issueDto.setCreated(issue.getCreated());
                        issueDto.setSprint(issue.getSprint().getId());
                        issueDto.setPoints(issue.getPoints());
                        issueDto.setPriority(issue.getPriority());
                        issueDto.setTags(issue.getTags());
                        issueDto.setStatus(issue.getStatus());
                        issueDto.setType(issue.getType());
                        issueDto.setTitle(issue.getTitle());
                        return issueDto;
                    })
                    .collect(Collectors.toList());

            getProjectResponse.setSprints(
                    retrievedProject.getSprints()
                            .stream()
                            .filter(Objects::nonNull)
                            .map(sprint -> {
                                SprintDto sprintDto = new SprintDto();

                                sprintDto.setId(sprint.getId());
                                sprintDto.setName(sprint.getName());
                                sprintDto.setStart(sprint.getStart());
                                sprintDto.setEnd(sprint.getEnd());
                                sprintDto.setGoal(sprint.getGoal());

                                if (sprint.getIssues() != null) {
                                    sprintDto.setIssues(
                                            sprint.getIssues().stream().map(Issue::getId).toList()
                                    );
                                } else {
                                    sprintDto.setIssues(new ArrayList<>());
                                }

                                return sprintDto;
                            })
                            .toList()
            );
        } else {
            issues = new ArrayList<>();
            getProjectResponse.setSprints(new ArrayList<>());
        }
        getProjectResponse.setIssues(issues);

        List<Issue> retrievedIssues = issuesService.getIssuesByAccessToken(accessToken);
        if (retrievedIssues != null) {
            List<IssueDto> noSprintIssues = retrievedIssues.stream()
                    .filter(issue -> issue.getSprint() == null)
                    .map(issue -> {
                        IssueDto issueDto = new IssueDto();
                        issueDto.setId(issue.getId());
                        issueDto.setAssignee(issue.getAssignee().getId());
                        issueDto.setCreated(issue.getCreated());
                        issueDto.setSprint(-1L);
                        issueDto.setPoints(issue.getPoints());
                        issueDto.setPriority(issue.getPriority());
                        issueDto.setTags(issue.getTags());
                        issueDto.setStatus(issue.getStatus());
                        issueDto.setType(issue.getType());
                        issueDto.setTitle(issue.getTitle());
                        return issueDto;
                    })
                    .toList();

            getProjectResponse.getIssues().addAll(
                    noSprintIssues
            );
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(getProjectResponse);
    }

}

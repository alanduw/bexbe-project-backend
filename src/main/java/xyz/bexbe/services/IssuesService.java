package xyz.bexbe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bexbe.dtos.IssueDto;
import xyz.bexbe.models.Issue;
import xyz.bexbe.models.Project;
import xyz.bexbe.models.Sprint;
import xyz.bexbe.models.User;
import xyz.bexbe.repositories.IssueRepository;
import xyz.bexbe.repositories.SprintRepository;
import xyz.bexbe.repositories.UserRepository;

import java.util.List;

@Service
public class IssuesService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectsService projectsService;

    public Issue createIssue(IssueDto issueDto, String accessToken) {
        User assignee = userRepository.findById(issueDto.getAssignee()).get();

        Sprint sprint = null;
        if (issueDto.getSprint()!= null && issueDto.getSprint() >= 0) {
            sprint = sprintRepository.findById(issueDto.getSprint()).get();
        }

        Project project = projectsService.getProjectByAccessToken(accessToken);

        Issue issue = new Issue();
        issue.setAssignee(assignee);
        issue.setSprint(sprint);
        issue.setTitle(issueDto.getTitle());
        issue.setPoints(issueDto.getPoints());
        issue.setPriority(issueDto.getPriority());
        issue.setStatus(issueDto.getStatus());
        issue.setType(issueDto.getType());
        issue.setCreated(issueDto.getCreated());
        issue.setTags(issueDto.getTags());
        issue.setProject(project);

        return issueRepository.save(issue);
    }

    public Issue updateIssue(IssueDto issueDto, Long issueId) {
        Issue foundIssue = issueRepository.findById(issueId).get();
        foundIssue.setStatus(issueDto.getStatus());
        return issueRepository.save(foundIssue);
    }

    public List<Issue> getIssuesByAccessToken(String accessToken) {
        Project project = projectsService.getProjectByAccessToken(accessToken);

        return issueRepository.findByProjectId(project.getId());
    }
}

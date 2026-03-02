package xyz.bexbe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bexbe.dtos.AddUserToProjectRequest;
import xyz.bexbe.dtos.IssueDto;
import xyz.bexbe.dtos.SprintDto;
import xyz.bexbe.dtos.UserDto;
import xyz.bexbe.models.Issue;
import xyz.bexbe.models.Project;
import xyz.bexbe.models.Sprint;
import xyz.bexbe.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DemoSeedService {
    @Autowired
    private SprintsService sprintsService;

    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private IssuesService issuesService;

    public void createFromDemoSeed(Project project) {
        AddUserToProjectRequest addUserToProjectRequest1 = new AddUserToProjectRequest();
        addUserToProjectRequest1.setAccessToken(project.getAccessToken());
        addUserToProjectRequest1.setUserFirstName("Katty");
        addUserToProjectRequest1.setUserLastName("Novak");
        addUserToProjectRequest1.setUserRole("Lead Dev");
        User addedUser1 = projectsService.addUserToProject(addUserToProjectRequest1);

        AddUserToProjectRequest addUserToProjectRequest2 = new AddUserToProjectRequest();
        addUserToProjectRequest2.setAccessToken(project.getAccessToken());
        addUserToProjectRequest2.setUserFirstName("Michael");
        addUserToProjectRequest2.setUserLastName("Chen");
        addUserToProjectRequest2.setUserRole("");
        User addedUser2 = projectsService.addUserToProject(addUserToProjectRequest2);

        AddUserToProjectRequest addUserToProjectRequest3 = new AddUserToProjectRequest();
        addUserToProjectRequest3.setAccessToken(project.getAccessToken());
        addUserToProjectRequest3.setUserFirstName("Andrew");
        addUserToProjectRequest3.setUserLastName("Patel");
        addUserToProjectRequest3.setUserRole("");
        User addedUser3 = projectsService.addUserToProject(addUserToProjectRequest3);

        SprintDto sprint1 = new SprintDto();
        sprint1.setName("Sprint 1");
        sprint1.setStart("2026-01-01");
        sprint1.setEnd("2026-01-14");
        sprint1.setGoal("Stabilize auth and fix routing bugs");
        Sprint createdSprint1 = sprintsService.createSprint(sprint1, project.getAccessToken());

        SprintDto sprint2 = new SprintDto();
        sprint2.setName("Sprint 2");
        sprint2.setStart("2026-01-15");
        sprint2.setEnd("2026-01-28");
        sprint2.setGoal("Performance improvements and mobile fixes");
        Sprint createdSprint2 = sprintsService.createSprint(sprint2, project.getAccessToken());

        SprintDto sprint3 = new SprintDto();
        sprint3.setName("Sprint 3");
        sprint3.setStart("2026-01-29");
        sprint3.setEnd("2026-02-11");
        sprint3.setGoal("Onboarding flow and 2FA");
        Sprint createdSprint3 = sprintsService.createSprint(sprint3, project.getAccessToken());

        SprintDto sprint4 = new SprintDto();
        sprint4.setName("Sprint 4");
        sprint4.setStart("2026-02-12");
        sprint4.setEnd("2026-02-25");
        sprint4.setGoal("Settings redesign and PDF export");
        Sprint createdSprint4 = sprintsService.createSprint(sprint4, project.getAccessToken());

        IssueDto issue1 = new IssueDto();
        issue1.setTitle("Login page crashes on mobile Safari");
        issue1.setType("bug");
        issue1.setPriority("critical");
        issue1.setStatus("in_progress");
        issue1.setAssignee(addedUser1.getId());
        issue1.setSprint(createdSprint2.getId());
        issue1.setPoints(3L);
        issue1.setCreated("2026-01-15");
        issue1.setTags("auth,mobile");
        issuesService.createIssue(issue1, project.getAccessToken());

        IssueDto issue2 = new IssueDto();
        issue2.setTitle("Implement dark mode toggle");
        issue2.setType("feature");
        issue2.setPriority("medium");
        issue2.setStatus("todo");
        issue2.setAssignee(addedUser2.getId());
        issue2.setSprint(createdSprint2.getId());
        issue2.setPoints(5L);
        issue2.setCreated("2026-01-14");
        issue2.setTags("ui,settings");
        issuesService.createIssue(issue2, project.getAccessToken());

        IssueDto issue3 = new IssueDto();
        issue3.setTitle("Refactor API rate limiting middleware");
        issue3.setType("task");
        issue3.setPriority("high");
        issue3.setStatus("review");
        issue3.setAssignee(addedUser3.getId());
        issue3.setSprint(createdSprint1.getId());
        issue3.setPoints(8L);
        issue3.setCreated("2026-01-13");
        issue3.setTags("api,performance");
        issuesService.createIssue(issue3, project.getAccessToken());

        IssueDto issue4 = new IssueDto();
        issue4.setTitle("User onboarding flow");
        issue4.setType("story");
        issue4.setPriority("high");
        issue4.setStatus("backlog");
        issue4.setAssignee(addedUser1.getId());
        issue4.setSprint(createdSprint3.getId());
        issue4.setPoints(13L);
        issue4.setCreated("2026-01-12");
        issue4.setTags("ux,onboarding");
        issuesService.createIssue(issue4, project.getAccessToken());

        IssueDto issue5 = new IssueDto();
        issue5.setTitle("Search returns stale cache results");
        issue5.setType("bug");
        issue5.setPriority("high");
        issue5.setStatus("todo");
        issue5.setAssignee(addedUser2.getId());
        issue5.setSprint(createdSprint2.getId());
        issue5.setPoints(2L);
        issue5.setCreated("2026-01-11");
        issue5.setTags("search,cache");
        issuesService.createIssue(issue5, project.getAccessToken());

        IssueDto issue6 = new IssueDto();
        issue6.setTitle("Export reports to PDF");
        issue6.setType("feature");
        issue6.setPriority("low");
        issue6.setStatus("backlog");
        issue6.setAssignee(addedUser3.getId());
        issue6.setSprint(createdSprint3.getId());
        issue6.setPoints(5L);
        issue6.setCreated("2026-01-10");
        issue6.setTags("reports");
        issuesService.createIssue(issue6, project.getAccessToken());

        IssueDto issue7 = new IssueDto();
        issue7.setTitle("Database query optimization");
        issue7.setType("task");
        issue7.setPriority("high");
        issue7.setStatus("in_progress");
        issue7.setAssignee(addedUser1.getId());
        issue7.setSprint(createdSprint2.getId());
        issue7.setPoints(8L);
        issue7.setCreated("2026-01-09");
        issue7.setTags("db,performance");
        issuesService.createIssue(issue7, project.getAccessToken());

        IssueDto issue8 = new IssueDto();
        issue8.setTitle("404 error on profile page redirect");
        issue8.setType("bug");
        issue8.setPriority("medium");
        issue8.setStatus("done");
        issue8.setAssignee(addedUser2.getId());
        issue8.setSprint(createdSprint1.getId());
        issue8.setPoints(1L);
        issue8.setCreated("2026-01-08");
        issue8.setTags("routing");
        issuesService.createIssue(issue8, project.getAccessToken());

        IssueDto issue9 = new IssueDto();
        issue9.setTitle("Notification system infrastructure");
        issue9.setType("epic");
        issue9.setPriority("critical");
        issue9.setStatus("in_progress");
        issue9.setAssignee(addedUser3.getId());
        issue9.setSprint(createdSprint2.getId());
        issue9.setPoints(21L);
        issue9.setCreated("2026-01-07");
        issue9.setTags("notifications,infra");
        issuesService.createIssue(issue9, project.getAccessToken());

        IssueDto issue10 = new IssueDto();
        issue10.setTitle("Two-factor authentication");
        issue10.setType("feature");
        issue10.setPriority("high");
        issue10.setStatus("todo");
        issue10.setAssignee(addedUser1.getId());
        issue10.setSprint(createdSprint3.getId());
        issue10.setPoints(8L);
        issue10.setCreated("2026-01-06");
        issue10.setTags("auth,security");
        issuesService.createIssue(issue10, project.getAccessToken());

        IssueDto issue11 = new IssueDto();
        issue11.setTitle("CSV import validation fails silently");
        issue11.setType("bug");
        issue11.setPriority("critical");
        issue11.setStatus("todo");
        issue11.setAssignee(addedUser2.getId());
        issue11.setSprint(createdSprint2.getId());
        issue11.setPoints(3L);
        issue11.setCreated("2026-01-05");
        issue11.setTags("import,data");
        issuesService.createIssue(issue11, project.getAccessToken());

        IssueDto issue12 = new IssueDto();
        issue12.setTitle("Redesign settings panel");
        issue12.setType("story");
        issue12.setPriority("low");
        issue12.setStatus("backlog");
        issue12.setAssignee(addedUser3.getId());
        issue12.setSprint(createdSprint4.getId());
        issue12.setPoints(5L);
        issue12.setCreated("2026-01-04");
        issue12.setTags("ui,settings");
        issuesService.createIssue(issue12, project.getAccessToken());
    }
}

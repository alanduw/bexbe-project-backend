package xyz.bexbe.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bexbe.dtos.IssueDto;
import xyz.bexbe.models.Issue;
import xyz.bexbe.services.IssuesService;

@RestController
@RequestMapping("/api/issues")
public class IssuesController {
    @Autowired
    private IssuesService issuesService;

    @PostMapping
    public String createIssue(@RequestBody IssueDto issueDto, @RequestParam String accessToken) throws JsonProcessingException {
        Issue createdIssue = issuesService.createIssue(issueDto, accessToken);

        IssueDto response = new IssueDto();
        response.setId(createdIssue.getId());
        response.setAssignee(createdIssue.getAssignee().getId());
        if (createdIssue.getSprint() != null) {
            response.setSprint(createdIssue.getSprint().getId());
        } else {
            response.setSprint(-1L);
        }
        response.setTitle(createdIssue.getTitle());
        response.setPoints(createdIssue.getPoints());
        response.setPriority(createdIssue.getPriority());
        response.setStatus(createdIssue.getStatus());
        response.setType(createdIssue.getType());
        response.setCreated(createdIssue.getCreated());
        response.setTags(createdIssue.getTags());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(response);
    }

    @PutMapping("/{id}")
    public String updateIssue(@RequestBody IssueDto issueDto, @PathVariable Long id) throws JsonProcessingException {
        Issue updatedIssue = issuesService.updateIssue(issueDto, id);
        issueDto.setStatus(updatedIssue.getStatus());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(issueDto);
    }
}

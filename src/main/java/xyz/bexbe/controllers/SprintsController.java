package xyz.bexbe.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.bexbe.dtos.SprintDto;
import xyz.bexbe.models.Issue;
import xyz.bexbe.models.Sprint;
import xyz.bexbe.services.SprintsService;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sprints")
public class SprintsController {
    @Autowired
    private SprintsService sprintsService;

    @PostMapping
    public String createSprint(@RequestBody SprintDto sprintDto, @RequestParam(required = true) String accessToken) throws JsonProcessingException {
        Sprint createdSprint = sprintsService.createSprint(sprintDto, accessToken);

        SprintDto response = new SprintDto();
        response.setId(createdSprint.getId());
        response.setName(createdSprint.getName());
        response.setStart(createdSprint.getStart());
        response.setEnd(createdSprint.getEnd());
        response.setGoal(createdSprint.getGoal());
        response.setIssues(createdSprint.getIssues() != null
                ? createdSprint.getIssues().stream().map(Issue::getId).collect(Collectors.toList())
                : new ArrayList<>());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(response);
    }
}

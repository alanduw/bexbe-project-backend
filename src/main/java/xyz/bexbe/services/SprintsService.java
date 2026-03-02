package xyz.bexbe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bexbe.dtos.SprintDto;
import xyz.bexbe.models.Project;
import xyz.bexbe.models.Sprint;
import xyz.bexbe.repositories.SprintRepository;

@Service
public class SprintsService {
    @Autowired
    private SprintRepository sprintRepository;

    @Autowired ProjectsService projectsService;

    public Sprint createSprint(SprintDto sprintDto, String accessToken)
    {
        Project project = projectsService.getProjectByAccessToken(accessToken);

        Sprint sprint = new Sprint();
        sprint.setName(sprintDto.getName());
        sprint.setStart(sprintDto.getStart());
        sprint.setEnd(sprintDto.getEnd());
        sprint.setGoal(sprintDto.getGoal());
        sprint.setProject(project);

        return sprintRepository.save(sprint);
    }
}

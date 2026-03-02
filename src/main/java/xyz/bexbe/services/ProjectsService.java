package xyz.bexbe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.bexbe.dtos.AddUserToProjectRequest;
import xyz.bexbe.dtos.CreateProjectRequest;
import xyz.bexbe.models.Project;
import xyz.bexbe.models.User;
import xyz.bexbe.repositories.ProjectRepository;
import xyz.bexbe.repositories.UserRepository;
import xyz.bexbe.utils.RandomString;

import java.util.ArrayList;

@Service
public class ProjectsService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    public Project createProject(CreateProjectRequest createProjectRequest) {
        Project project = new Project();
        project.setAccessToken(RandomString.generate(32));

        User owner = userRepository.save(constructUser(
                createProjectRequest.getOwnerFirstName(),
                createProjectRequest.getOwnerLastName(),
                createProjectRequest.getOwnerRole()
        ));

        project.setOwner(owner);
        project.setUsers(new ArrayList<>());

        return projectRepository.save(project);
    }

    public User addUserToProject(AddUserToProjectRequest addUserToProjectRequest) {
        Project project = getProjectByAccessToken(addUserToProjectRequest.getAccessToken());

        User user = userRepository.save(constructUser(
                addUserToProjectRequest.getUserFirstName(),
                addUserToProjectRequest.getUserLastName(),
                addUserToProjectRequest.getUserRole()
        ));

        project.getUsers().add(user);
        projectRepository.save(project);

        return user;
    }

    public Project getProjectByAccessToken(String accessToken) {
        return projectRepository.findByAccessToken(accessToken);
    }

    private User constructUser(String _firstName, String _lastName, String _role) {
        final String firstName = _firstName != null ? _firstName : "";
        final String lastName = _lastName != null ? _lastName : "";
        final String abrName = (!firstName.isEmpty() ? "" + firstName.charAt(0): "")
                + (!lastName.isEmpty() ? "" + lastName.charAt(0): "");
        final String role = _role != null ? _role : "";

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAbrName(abrName);
        user.setRole(role);

        return user;
    }
}

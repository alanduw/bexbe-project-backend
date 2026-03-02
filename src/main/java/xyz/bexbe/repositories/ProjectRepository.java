package xyz.bexbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bexbe.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByAccessToken(String accessToken);
}

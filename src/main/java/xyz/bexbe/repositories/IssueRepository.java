package xyz.bexbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bexbe.models.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByProjectId(Long projectId);
}

package xyz.bexbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.bexbe.models.Sprint;

public interface SprintRepository extends JpaRepository<Sprint, Long>  {
}

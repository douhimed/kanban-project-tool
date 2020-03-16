package org.adex.repositories;

import org.adex.web.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findByProjectIdentifierOrderByPriority(String identifier);

    Optional<Task> findByProjectSequence(String projectSequence);

}

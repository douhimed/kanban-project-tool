package org.adex.repositories;

import java.util.Optional;

import org.adex.web.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	Optional<Project> findProjectByProjectIdentifier(String projectIdentifier);

	Iterable<Project> findAllByProjectLeader(String projectLeader);
	
}

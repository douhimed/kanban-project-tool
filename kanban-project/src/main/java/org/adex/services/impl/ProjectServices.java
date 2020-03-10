package org.adex.services.impl;

import java.util.List;
import java.util.Optional;

import org.adex.repositories.ProjectRepository;
import org.adex.services.IProjectServices;
import org.adex.utilities.exceptions.id.ProjectIdException;
import org.adex.web.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectServices implements IProjectServices {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project saveOrUpdate(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return this.projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project identifier : " + project.getProjectIdentifier() + " already exist");
		}
	}

	@Override
	public Iterable<Project> getAllProjects() {
		return this.projectRepository.findAll();
	}

	@Override
	public Project findProjectByIdentifier(String projectIdentifier) {
		Optional<Project> opt = this.projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
		if(!opt.isPresent())
			throw new ProjectIdException(
					"Project identifier : " + projectIdentifier.toUpperCase() + " does not exist");
		return opt.get();
	}

	@Override
	public Project deleteProjectByIdentifier(String projectIdentifier) {
		Project deletedProject = this.findProjectByIdentifier(projectIdentifier);
		this.projectRepository.delete(deletedProject);
		return deletedProject;
	}

}

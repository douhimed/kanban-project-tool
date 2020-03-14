package org.adex.services.impl;

import java.util.List;
import java.util.Optional;

import org.adex.repositories.BacklogRepository;
import org.adex.repositories.ProjectRepository;
import org.adex.services.IProjectServices;
import org.adex.utilities.exceptions.ProjectIdException;
import org.adex.web.models.Backlog;
import org.adex.web.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectServices implements IProjectServices {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	@Override
	public Project saveOrUpdate(Project project) {
		String identifier = project.getProjectIdentifier().toUpperCase();
		try {
			project.setProjectIdentifier(identifier);
			if(project.getId() == null){
				Backlog backlog = Backlog.builder().project(project).projectIdentifier(identifier).build();
				project.setBacklog(backlog);
			}else if(project.getId() != null){
				project.setBacklog(this.backlogRepository.findByProjectIdentifier(identifier));
			}
			return this.projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project identifier : " + identifier + " already exist");
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

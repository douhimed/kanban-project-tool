package org.adex.services.impl;

import java.util.List;
import java.util.Optional;

import org.adex.repositories.BacklogRepository;
import org.adex.repositories.ProjectRepository;
import org.adex.repositories.UserRepository;
import org.adex.services.IProjectServices;
import org.adex.utilities.exceptions.ProjectIdException;
import org.adex.utilities.exceptions.ProjectNotFoundException;
import org.adex.web.models.Backlog;
import org.adex.web.models.Project;
import org.adex.web.models.User;
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

	@Autowired
	private UserRepository UserRepository;

	@Override
	public Project saveOrUpdate(Project project, String projectLeader) {

		if (project.getId() != null)
			this.findProjectByIdentifier(project.getProjectIdentifier(), projectLeader);

		User user = this.UserRepository.findByUsername(projectLeader).get();
		project.setUser(user);
		project.setProjectLeader(user.getUsername());
		return this.saveOrUpdate(project);

	}

	@Override
	public Project saveOrUpdate(Project project) {
		String identifier = project.getProjectIdentifier().toUpperCase();
		try {

			project.setProjectIdentifier(identifier);
			if (project.getId() == null) {
				Backlog backlog = Backlog.builder().project(project).projectIdentifier(identifier).build();
				project.setBacklog(backlog);
			} else if (project.getId() != null) {
				project.setBacklog(this.backlogRepository.findByProjectIdentifier(identifier));
			}
			return this.projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project identifier : " + identifier + " already exist");
		}
	}

	@Override
	public Iterable<Project> getAllProjects() {
		return this.projectRepository.findAll();
	}

	@Override
	public Iterable<Project> getAllProjects(String projectLeader) {
		return this.projectRepository.findAllByProjectLeader(projectLeader);
	}

	@Override
	public Project findProjectByIdentifier(String projectIdentifier) {
		Optional<Project> opt = this.projectRepository.findProjectByProjectIdentifier(projectIdentifier.toUpperCase());
		if (!opt.isPresent())
			throw new ProjectIdException("Project identifier : " + projectIdentifier.toUpperCase() + " does not exist");
		return opt.get();
	}

	@Override
	public Project findProjectByIdentifier(String projectIdentifier, String projectLeader) { 
		Project project = this.findProjectByIdentifier(projectIdentifier);
		if (!project.getProjectLeader().equals(projectLeader))
			throw new ProjectNotFoundException("Project not found under this account");
		return project;
	}

	@Override
	public Project deleteProjectByIdentifier(String projectIdentifier) {
		Project deletedProject = this.findProjectByIdentifier(projectIdentifier);
		this.projectRepository.delete(deletedProject);
		return deletedProject;
	}

	@Override
	public Project deleteProjectByIdentifier(String projectIdentifier, String projectLeader) {
		Project deletedProject = this.findProjectByIdentifier(projectIdentifier, projectLeader);
		this.projectRepository.delete(deletedProject);
		return deletedProject;
	}

}

package org.adex.services;


import org.adex.web.models.Project;

public interface IProjectServices {

	Project saveOrUpdate(Project project);

	Iterable<Project> getAllProjects();

	Project findProjectByIdentifier(String projectIdentifier);
	
	Project deleteProjectByIdentifier(String projectIdentifier);

}

package org.adex.services;


import javax.validation.Valid;

import org.adex.web.models.Project;

public interface IProjectServices {

	Project saveOrUpdate(Project project);

	Iterable<Project> getAllProjects();

	Project findProjectByIdentifier(String projectIdentifier);
	
	Project deleteProjectByIdentifier(String projectIdentifier);

	Project saveOrUpdate(@Valid Project project, String name);

	Iterable<Project> getAllProjects(String name);

	Project findProjectByIdentifier(String projectIdentifier, String projectLeader);
	
	Project deleteProjectByIdentifier(String projectIdentifier, String projectLeader);


}

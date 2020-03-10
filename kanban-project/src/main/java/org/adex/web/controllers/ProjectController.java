package org.adex.web.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.adex.services.IErrorServices;
import org.adex.services.IProjectServices;
import org.adex.web.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private IProjectServices projectServices;

	@Autowired
	private IErrorServices errorServices;

	@GetMapping
	public ResponseEntity<Iterable<Project>> getAll() {
		return new ResponseEntity<Iterable<Project>>(this.projectServices.getAllProjects(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> saveNewProject(@Valid @RequestBody Project project, BindingResult result) {

		Optional<ResponseEntity<Map<String, String>>> errorsOptional = this.errorServices.validate(result);
		if (errorsOptional.isPresent())
			return errorsOptional.get();

		return new ResponseEntity<Project>(this.projectServices.saveOrUpdate(project), HttpStatus.CREATED);
	}

	@GetMapping("{projectIdentifier}")
	public ResponseEntity<Project> searchProjectById(@PathVariable String projectIdentifier) {
		return new ResponseEntity<Project>(this.projectServices.findProjectByIdentifier(projectIdentifier),
				HttpStatus.OK);
	}

	@DeleteMapping("{projectIdentifier}")
	public ResponseEntity<?> deleteByIdentifier(@PathVariable String projectIdentifier) {
		return new ResponseEntity<Project>(this.projectServices.deleteProjectByIdentifier(projectIdentifier),
				HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, BindingResult result) {

		Optional<ResponseEntity<Map<String, String>>> errorsOptional = this.errorServices.validate(result);
		if (errorsOptional.isPresent())
			return errorsOptional.get();

		return new ResponseEntity<Project>(this.projectServices.saveOrUpdate(project), HttpStatus.CREATED);
	}

}

package org.adex.services.impl;

import org.adex.repositories.BacklogRepository;
import org.adex.repositories.ProjectRepository;
import org.adex.repositories.TaskRepository;
import org.adex.services.IProjectServices;
import org.adex.services.ITaskServices;
import org.adex.utilities.exceptions.ProjectNotFoundException;
import org.adex.utilities.exceptions.TaskNotFoundException;
import org.adex.web.models.Backlog;
import org.adex.web.models.Project;
import org.adex.web.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Service
@Transactional
public class TaskServices implements ITaskServices {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private IProjectServices projectServices;


	@Override
	public Task addTask(String projectIdentifier, @Valid Task task, String projectLeader) {
		 Backlog backlog = this.projectServices.findProjectByIdentifier(projectIdentifier, projectLeader).getBacklog();
         task.setBacklog(backlog); 
         int backlogSequence = backlog.getPTSequence();
         backlogSequence++;
         backlog.setPTSequence(backlogSequence);
         task.setProjectSequence(projectIdentifier + "-" + backlogSequence);
         task.setProjectIdentifier(projectIdentifier);

         if (task.getPriority() == null || task.getPriority()==0)
         	task.setPriority(3);

         if (task.getStatus() == "" || task.getStatus() == null)
             task.setStatus("TO_DO");
         return this.taskRepository.save(task);
	}
	
    @Override
    public List<Task> findBacklogById(String projectIdentifier, String projectLeader) {
    	this.projectServices.findProjectByIdentifier(projectIdentifier, projectLeader);
        return this.taskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier).get();
    }


    @Override
    public Task findTaskByProjectSequence(String backlogId, String projectSequence, String projectLeader) {

       this.projectServices.findProjectByIdentifier(backlogId, projectLeader);

        Optional<Task> optionalTask = this.taskRepository.findByProjectSequence(projectSequence);
        if (optionalTask.isEmpty())
            throw new TaskNotFoundException("Task with the given project sequence does not exist");

        Task task = optionalTask.get();
        if (!task.getProjectIdentifier().equals(backlogId))
            throw new TaskNotFoundException("Task with the id " + projectSequence + " does not exist in this project " + backlogId);

        return task;

    }

    @Override
    public Task updateTaskByProjectSequence(Task updatedTask, String backlogSequence, String taskId, String projectLeader) {
        Task oldTask = this.findTaskByProjectSequence(backlogSequence, taskId, projectLeader);
        oldTask = updatedTask;
        return this.taskRepository.save(oldTask);
    }

    @Override
    public Task deleteBySequence(String backlogId, String taskId, String projectLeader) {
        Task deletedTask = this.findTaskByProjectSequence(backlogId, taskId, projectLeader);
        this.taskRepository.delete(deletedTask);
        return deletedTask;
    }


}

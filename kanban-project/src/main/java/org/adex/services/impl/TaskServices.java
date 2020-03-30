package org.adex.services.impl;

import org.adex.repositories.BacklogRepository;
import org.adex.repositories.ProjectRepository;
import org.adex.repositories.TaskRepository;
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

@Service
@Transactional
public class TaskServices implements ITaskServices {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Task addTask(String projectIdentifier, Task task) {
        try {
            Backlog backlog = this.backlogRepository.findByProjectIdentifier(projectIdentifier);
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
        } catch (Exception e) {
        	System.err.println(e.getMessage());
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @Override
    public List<Task> findBacklogById(String backlogId) {
        return getProject(backlogId).get().getBacklog().getTasks();
    }


    @Override
    public Task findTaskByProjectSequence(String backlogId, String projectSequence) {

        this.getProject(backlogId).get().getBacklog().getTasks();

        Optional<Task> optionalTask = this.taskRepository.findByProjectSequence(projectSequence);
        if (optionalTask.isEmpty())
            throw new TaskNotFoundException("Task with the given project sequence does not exist");

        Task task = optionalTask.get();
        if (!task.getProjectIdentifier().equals(backlogId))
            throw new TaskNotFoundException("Task with the id " + projectSequence + " does not exist in this project " + backlogId);

        return task;

    }

    @Override
    public Task updateTaskByProjectSequence(Task updatedTask, String backlogSequence, String taskId) {
        Task oldTask = this.findTaskByProjectSequence(backlogSequence, taskId);
        oldTask = updatedTask;
        return this.taskRepository.save(oldTask);
    }

    @Override
    public Task deleteBySequence(String backlogId, String taskId) {
        Task deletedTask = this.findTaskByProjectSequence(backlogId, taskId);
        this.taskRepository.delete(deletedTask);
        return deletedTask;
    }

    private Optional<Project> getProject(String backlogId) {
        Optional<Project> optionalProject = this.projectRepository.findProjectByProjectIdentifier(backlogId);
        if (optionalProject.isEmpty())
            throw new ProjectNotFoundException("Project with the given ID does not exist");
        return optionalProject;
    }
}

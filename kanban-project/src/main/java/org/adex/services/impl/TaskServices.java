package org.adex.services.impl;

import org.adex.repositories.BacklogRepository;
import org.adex.repositories.ProjectRepository;
import org.adex.repositories.TaskRepository;
import org.adex.services.ITaskServices;
import org.adex.utilities.exceptions.id.ProjectNotFoundException;
import org.adex.web.models.Backlog;
import org.adex.web.models.Project;
import org.adex.web.models.Task;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            System.out.println(backlog);
            task.setBacklog(backlog);
            int backlogSequence = backlog.getPTSequence();
            backlogSequence++;
            backlog.setPTSequence(backlogSequence);
            task.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            task.setProjectIdentifier(projectIdentifier);

            if (task.getPriority() == null)
                task.setPriority(3);

            if (task.getStatus() == "" || task.getStatus() == null)
                task.setStatus("TO_DO");
            return this.taskRepository.save(task);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    @Override
    public List<Task> findBacklogById(String backlogId) {

        Optional<Project> optionalProject = this.projectRepository.findProjectByProjectIdentifier(backlogId);
        if (optionalProject.isEmpty())
            throw new ProjectNotFoundException("Project with the given ID does not exist");
        return optionalProject.get().getBacklog().getTasks();

    }


}

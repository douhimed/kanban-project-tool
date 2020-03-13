package org.adex.services.impl;

import org.adex.repositories.BacklogRepository;
import org.adex.repositories.TaskRepository;
import org.adex.services.ITaskServices;
import org.adex.web.models.Backlog;
import org.adex.web.models.Task;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServices implements ITaskServices {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(String projectIdentifier, Task task) {
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
    }
}

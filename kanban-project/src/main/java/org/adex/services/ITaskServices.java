package org.adex.services;

import org.adex.web.models.Task;

import java.util.List;

public interface ITaskServices {

    Task addTask(String projectIdentifier, Task task);

    List<Task> findBacklogById(String backlogId);

    Task findTaskByProjectSequence(String backlogId, String projectSequence);
}

package org.adex.services;

import org.adex.web.models.Task;

import java.util.List;

import javax.validation.Valid;

public interface ITaskServices {

	Task addTask(String backlogId, @Valid Task task, String projectLeader);

	List<Task> findBacklogById(String backlogId, String projectLeader);

    Task findTaskByProjectSequence(String backlogId, String projectSequence, String projectLeader);

    Task updateTaskByProjectSequence(Task updatedTask, String backlogSequence, String taskId, String projectLeader);

    Task deleteBySequence(String backlogId, String taskId, String projectLeader);


}

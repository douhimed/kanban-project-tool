package org.adex.services;

import org.adex.web.models.Task;

public interface ITaskServices {

    Task addTask(String projectIdentifier, Task task);
}

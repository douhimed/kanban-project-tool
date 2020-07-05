package org.adex.web.controllers;

import org.adex.services.IErrorServices;
import org.adex.services.ITaskServices;
import org.adex.web.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ITaskServices taskServices;

    @Autowired
    private IErrorServices errorServices;

    @PostMapping("/{backlogId}")
    public ResponseEntity<?> addTask(@Valid @RequestBody Task task, BindingResult result, @PathVariable String backlogId, Principal principal) {

        Optional<ResponseEntity<Map<String, String>>> errorsOptional = this.errorServices.validate(result);
        return errorsOptional.isPresent() ? errorsOptional.get() : new ResponseEntity<Task>(this.taskServices.addTask(backlogId, task, principal.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/{backlogId}")
    public ResponseEntity<List<Task>> getBackLog(@PathVariable String backlogId, Principal principal){
        return new ResponseEntity<List<Task>>(this.taskServices.findBacklogById(backlogId, principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/{backlogId}/{taskId}")
    public ResponseEntity<?> getBackLog(@PathVariable String backlogId, @PathVariable String taskId, Principal principal){
        return new ResponseEntity<Task>(this.taskServices.findTaskByProjectSequence(backlogId, taskId, principal.getName()), HttpStatus.OK);
    }

    @PutMapping("/{backlogId}/{taskId}")
    public ResponseEntity<?> updateTask(@Valid @RequestBody Task task, BindingResult result, @PathVariable String backlogId, @PathVariable String taskId, Principal principal) {

        Optional<ResponseEntity<Map<String, String>>> errorsOptional = this.errorServices.validate(result);
        if (errorsOptional.isPresent())
            return errorsOptional.get();

        Task updatedTask = this.taskServices.updateTaskByProjectSequence(task, backlogId, taskId, principal.getName());
        return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlogId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String backlogId, @PathVariable String taskId, Principal principal){
        return new ResponseEntity<Task>(this.taskServices.deleteBySequence(backlogId, taskId, principal.getName()), HttpStatus.OK);
    }

}

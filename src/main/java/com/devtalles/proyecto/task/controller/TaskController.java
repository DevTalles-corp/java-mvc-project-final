package com.devtalles.proyecto.task.controller;

import com.devtalles.proyecto.task.excepciones.TaskException;
import com.devtalles.proyecto.task.excepciones.TaskValidationException;
import com.devtalles.proyecto.task.model.Task;
import com.devtalles.proyecto.task.model.TaskRepository;

import java.util.List;

public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);
        Task task = new Task(id, title, description, completed);
        this.taskRepository.save(task);
        System.out.println("La tarea fue agregada exitosamente.");
    }

    public void removeTask(String id) throws TaskValidationException, TaskException {
        if(id==null || id.trim().isEmpty()){
            throw new TaskValidationException("El id no puede estar vacío");
        }
        this.taskRepository.remove(id);

    }

    public void showsTaks() throws TaskValidationException, TaskException {
        List<Task> tasks = this.taskRepository.findAll();
        if(tasks.isEmpty()){
            throw new TaskValidationException("La lista no puede estar vacía");
        }
        for(Task task: tasks){
            System.out.println(task);
        }
    }

    public void updateTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);
        Task updateTask = new Task(id, title, description, completed);
        this.taskRepository.updateTask(updateTask);
    }

    private void validateTaskData(String id, String title, String description, Boolean completed) throws TaskValidationException {
        if(id==null || id.trim().isEmpty()){
            throw new TaskValidationException("El id no puede estar vacío");
        }

        if(title==null || title.trim().isEmpty()){
            throw new TaskValidationException("El título no puede estar vacío");
        }

        if(description==null || description.trim().isEmpty()){
            throw new TaskValidationException("La descripción no puede estar vacío");
        }

        if(completed==null ){
            throw new TaskValidationException("El estado no puede nulo");
        }
    }
}











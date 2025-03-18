package com.devtalles.proyecto.task.model;

import com.devtalles.proyecto.task.excepciones.TaskException;
import com.devtalles.proyecto.task.persistence.TaskPersistence;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    List<Task> tasks;

    public TaskRepository() {
        tasks = TaskPersistence.loadTasks();
    }

    public void save(Task task) throws TaskException {
        if(tasks.contains(task)){
            throw new TaskException("La tarea ya existe en nuestra BD");
        }
        tasks.add(task);
        TaskPersistence.saveTasks(tasks);
    }

    public Task findById(String id){
        for (Task task: tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

    public List<Task> findCompletedTasks() throws TaskException {

        List<Task> completedTasks = new ArrayList<>();
        for (Task task: tasks){
            if(task.getCompleted()){
                completedTasks.add(task);
            }
        }

        if(completedTasks.isEmpty()){
            throw new TaskException("No hay tareas completadas");
        }
        return completedTasks;
    }

    public List<Task> findPendingTasks() throws TaskException {

        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.getCompleted()) {
                pendingTasks.add(task);
            }
        }

        if (pendingTasks.isEmpty()) {
            throw new TaskException("No hay tareas pendientes");
        }
        return pendingTasks;
    }



    public void remove(String id) throws TaskException {
        Task task = findById(id);
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }

    public void remove(Task task) throws TaskException {
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }

        if(!tasks.contains(task)){
            throw new TaskException("La tarea no existe en la lista");
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }

    public List<Task> findAll() throws TaskException {
        if(tasks.isEmpty()){
            throw new TaskException("La lista esta vacia");
        }
        return tasks;
    }

    public int findIndexById(String id){
        for(int i=0; i<tasks.size(); i++){
            if (tasks.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public void updateTask(Task updateTask) throws TaskException {
        if(updateTask==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        int index = findIndexById(updateTask.getId());
        if(index==-1){
            throw new TaskException("El índice no es válido");
        }
        tasks.set(index, updateTask);
        TaskPersistence.saveTasks(tasks);
    }

    public void updateTaskCompleted(String id, Boolean completed) throws TaskException {

        int index = findIndexById(id);
        if(index==-1){
            throw new TaskException("El índice no es válido");
        }
        tasks.get(index).setCompleted(completed);
        TaskPersistence.saveTasks(tasks);
    }


}

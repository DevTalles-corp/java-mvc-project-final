package com.devtalles.proyecto.task.model;

import com.devtalles.proyecto.task.excepciones.TaskException;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    List<Task> tasks = new ArrayList<>();

    public void save(Task task) throws TaskException {
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        tasks.add(task);
    }

    public Task findById(String id){
        for (Task task: tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

    public void remove(String id) throws TaskException {
        Task task = findById(id);
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        tasks.remove(task);
    }

    public void remove(Task task) throws TaskException {
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }

        if(!tasks.contains(task)){
            throw new TaskException("La tarea no existe en la lista");
        }
        tasks.remove(task);
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
    }
}

package com.devtalles.proyecto.task.view;

import com.devtalles.proyecto.task.controller.TaskController;
import com.devtalles.proyecto.task.excepciones.TaskException;
import com.devtalles.proyecto.task.excepciones.TaskValidationException;
import com.devtalles.proyecto.task.model.Task;

import java.util.Scanner;

public class TaskView {
    private final TaskController taskController;
    private final Scanner scanner;

    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu(){
        while (true){
            System.out.println("\n Gestión de Tareas");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Eliminar Tarea");
            System.out.println("3. Actualizar Tarea");
            System.out.println("4. Mostrar Tareas");
            System.out.println("5. Actualizar estado de la tarea");
            System.out.println("6. Mostrar Tareas completadas");
            System.out.println("7. Mostrar Tareas pendientes");
            System.out.println("8. Salir");
            System.out.println("Seleccione una opción");

            String option = scanner.nextLine();
            switch (option){
                case "1":
                    addTaskView();
                break;
                case "2":
                    removeTaskView();
                break;
                case "3":
                    updateTaskView();
                break;
                case "4":
                    showTaskView();
                break;
                case "5":
                    updateTaskCompletedView();
                break;
                case "6":
                    showCompletedTasksView();
                break;
                case "7":
                    showPendingTasksView();
                break;
                case "8":
                    System.out.println("Saliendo del sistema");
                return;
                default:
                    System.out.println("Opción invalida, Intente nuevamente.");
            }
        }
    }

    public void addTaskView(){
        try {
            Task task = getTaskInput();
            taskController.addTask(task.getId(), task.getTitle(),task.getDescription(), task.getCompleted());
            System.out.println("Tarea agregada correctamente");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado, Contacte con el soporte");
            e.printStackTrace();
        }
    }

    public void removeTaskView(){
        try {
            System.out.println("Ingrese el Id a eliminar");
            String id = scanner.nextLine();
            this.taskController.removeTask(id);
            System.out.println("Tarea eliminada correctamente");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado, Contacte con el soporte");
            e.printStackTrace();
        }
    }

    public void showTaskView(){
        try {
            System.out.println("\nLa lista de Tareas");
            this.taskController.showTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado, Contacte con el soporte");
            e.printStackTrace();
        }
    }

    public void updateTaskView(){
        try {
            Task task = getTaskInput();
            taskController.updateTask(task.getId(), task.getTitle(),task.getDescription(), task.getCompleted());
            System.out.println("Tarea actualizada correctamente");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado, Contacte con el soporte");
            e.printStackTrace();
        }
    }

    public void updateTaskCompletedView(){
        try {
            System.out.println("Ingrese el ID de la Tarea");
            String id = scanner.nextLine();

            Boolean completed=null;
            while (completed==null) {
                System.out.println("¿Está completada? true/false");
                String input = scanner.nextLine().trim().toLowerCase();
                if(input.equals("true")){
                    completed = true;
                }else if(input.equals("false")){
                    completed = false;
                }else{
                    System.out.println("El valor ingresado no es correcto, ingrese: 'true' o 'false'");
                }
            }
            taskController.updateTaskCompleted(id, completed);
            System.out.println("Estado de la tarea actualizado correctamente");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado, Contacte con el soporte");
            e.printStackTrace();
        }
    }

    public void showCompletedTasksView(){
        try{
            System.out.println("Tareas Completadas");
            taskController.showCompletedTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public void showPendingTasksView(){
        try{
            System.out.println("Tareas Pendientes");
            taskController.showPendingTsaks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error" + e.getMessage());
        }
    }


    private Task getTaskInput(){
        String id;
        do {
            System.out.println("Ingresar ID");
            id = scanner.nextLine();
            if(id.isEmpty()) {
                System.out.println("El id no puede estar vacío");
            }
        }while (id.isEmpty());

        String title;
        do {
            System.out.println("Ingrese el Título");
            title = scanner.nextLine();
            if(title.isEmpty()) {
                System.out.println("El título no puede estar vacío");
            }
        }while (title.isEmpty());

        String description;
        do {
            System.out.println("Ingresar la descripción");
            description = scanner.nextLine();
            if(description.isEmpty()) {
                System.out.println("La descripción no puede estar vacía");
            }
        }while (description.isEmpty());

        Boolean completed=null;
        while (completed==null) {
            System.out.println("¿Está completada? true/false");
            String input = scanner.nextLine().trim().toLowerCase();
            if(input.equals("true")){
                completed = true;
            }else if(input.equals("false")){
                completed = false;
            }else{
                System.out.println("El valor ingresado no es correcto, ingrese: 'true' o 'false'");
            }

        }
        return new Task(id, title, description, completed);
    }
}
package com.devtalles.proyecto;

import com.devtalles.proyecto.task.controller.TaskController;
import com.devtalles.proyecto.task.model.TaskRepository;
import com.devtalles.proyecto.task.view.TaskView;

public class Main {
    public static void main(String[] args) {

        TaskRepository repository = new TaskRepository();
        TaskController controller = new TaskController(repository);
        TaskView view = new TaskView(controller);

        view.showMenu();

    }
}
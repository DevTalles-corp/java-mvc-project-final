package com.devtalles.proyecto.task.persistence;

import com.devtalles.proyecto.task.model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskPersistence {
    private final static String FILE_PATH = "tasks.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveTasks(List<Task> tasks){
        try(Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(tasks, writer);
        }catch (IOException e){
            System.out.println( "Error: " + e.getMessage());
        }
    }

    public static List<Task> loadTasks(){
        File file = new File(FILE_PATH);
        if(!file.exists()){
            return new ArrayList<>();
        }

        try(Reader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(reader, listType);
        }catch (IOException e){
            System.out.println( "Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}












package main;

import main.resoponse.TodoItem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
//    private static Map<Integer, TodoItem> todoMap = new HashMap<>();
    private static ConcurrentHashMap<Integer, TodoItem> todoMap = new ConcurrentHashMap<>();
//    private static int currentId = 1;
    private static AtomicInteger currentId = new AtomicInteger(1);

    public static long  addTodoItem(TodoItem item){
//        int id = currentId++;
        int id = currentId.getAndIncrement();
        item.setId(id);
        todoMap.put(id, item);
        return id;
    }

    public static List<TodoItem> getAllTodoItems(){
        return new ArrayList<>(todoMap.values());
    }

    public static TodoItem getItemById(int id){
        if(todoMap.containsKey(id)){
            return todoMap.get(id);
        }
        return null;
    }

    public static void removeTodoItem(int id){
        if(todoMap.containsKey(id)){
            todoMap.remove(id);
        }
    }
}

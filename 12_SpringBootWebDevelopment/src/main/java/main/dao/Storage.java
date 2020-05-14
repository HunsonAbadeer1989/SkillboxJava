package main.repo;

import main.model.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage  {
    private static ConcurrentHashMap<Integer, TodoItem> todoMap = new ConcurrentHashMap<>();
    private static AtomicInteger currentId = new AtomicInteger(1);

    public static long addTodoItem(TodoItem item) {
        int id = currentId.getAndIncrement();
        item.setId(id);
        todoMap.put(id, item);
        return id;
    }

    public static List<TodoItem> getAllTodoItems() {
        return new ArrayList<>(todoMap.values());
    }

    public static TodoItem getItemById(int id) {
        return todoMap.get(id);
    }

    public static void removeTodoItem(int id) {
        todoMap.remove(id);
    }
}

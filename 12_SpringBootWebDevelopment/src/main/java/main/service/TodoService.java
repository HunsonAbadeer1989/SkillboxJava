package main.service;

import main.dao.TodoRepo;
import main.model.TodoItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class TodoService {

    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @Transactional(readOnly = true)
    public Iterable<TodoItem> todoList() {
        return todoRepo.findAll();
    }

    public int add(TodoItem item) {
        TodoItem newItem = todoRepo.save(item);
        return newItem.getId();
    }

    @Transactional(readOnly = true)
    public Optional<TodoItem> getTodoItemById(int id){
        TodoItem todoItem = todoRepo.findById(id);
        if(todoItem == null){
//            return Optional.of(new TodoItem(0, "There is no item by Id=" + id, false));
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return Optional.of(todoItem);
    }

    public void deleteItem(int id) {
        this.todoRepo.deleteById(id);
    }

    public void update(int id, TodoItem todoItem) {
        TodoItem updateTodoItem = todoRepo.findById(id);
        updateTodoItem.setTitle(todoItem.getTitle());
        updateTodoItem.setDone(todoItem.isDone());
    }

}

package main.service;

import main.dao.TodoRepo;
import main.model.TodoItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TodoService {

    private final TodoRepo todoRepo;

    public TodoService(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    public Iterable<TodoItem> todoList(){
        return todoRepo.findAll();
    }

    public ResponseEntity<TodoItem> getTodoItem(int id){
        TodoItem todoItem = todoRepo.findById(id);
        if(todoItem == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new TodoItem("Have no that business"));
        }
        return new ResponseEntity<>(todoItem, HttpStatus.OK);
    }

    public int add(TodoItem item){
        TodoItem newItem =  todoRepo.save(item);
        return newItem.getId();
    }

    public void deleteItem(int id){
        todoRepo.deleteById(id);
    }

    public void update(int id, TodoItem todoItem){
        TodoItem updateTodoItem = todoRepo.findById(id);
        updateTodoItem.setTitle(todoItem.getTitle());
        updateTodoItem.setDone(todoItem.isDone());
        todoRepo.save(updateTodoItem);
    }
}

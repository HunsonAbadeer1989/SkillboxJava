package main.controller;

import main.dao.TodoRepo;
import main.model.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class TodoController {

    @Autowired
    private TodoRepo todoRepo;

    @GetMapping()
    public List<TodoItem> todoList(){
        Iterable<TodoItem> todoItems = todoRepo.findAll();
        ArrayList<TodoItem> list = new ArrayList<>();
        todoItems.forEach(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable int id){
        TodoItem todoItem = todoRepo.findById(id);
        if(todoItem == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new TodoItem("Have no that business"));
        }
        return new ResponseEntity<>(todoItem, HttpStatus.OK);
    }

    @PostMapping()
    public int add(@Valid @NotNull @RequestBody TodoItem item){
        TodoItem newItem =  todoRepo.save(item);
        return newItem.getId();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable("id") int id){
        todoRepo.deleteById(id);
    }

    @PutMapping(path ="/{id}")
    public void update(@PathVariable("id") int id, @Valid @NotNull @RequestBody TodoItem todoItem){
        TodoItem updateTodoItem = todoRepo.findById(id);
        updateTodoItem.setTitle(todoItem.getTitle());
        updateTodoItem.setDone(todoItem.isDone());
        todoRepo.save(updateTodoItem);
    }
}
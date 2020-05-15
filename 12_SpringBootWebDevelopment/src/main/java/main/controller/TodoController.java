package main.controller;

import main.service.TodoService;
import main.model.TodoItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public Iterable<TodoItem> todoList(){
        return todoService.todoList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable int id){
        return todoService.getTodoItem(id);
    }

    @PostMapping()
    public int add(@Valid @NotNull @RequestBody TodoItem item){
        return todoService.add(item);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable("id") int id){
        todoService.deleteItem(id);
    }

    @PutMapping(path ="/{id}")
    public void update(@PathVariable("id") int id, @Valid @NotNull @RequestBody TodoItem todoItem){
        todoService.update(id, todoItem);
    }
}
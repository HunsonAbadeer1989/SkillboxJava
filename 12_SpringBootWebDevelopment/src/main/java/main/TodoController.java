package main;

import main.resoponse.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/")
public class TodoController {

    @GetMapping()
    public List<TodoItem> todoList(){
        return Storage.getAllTodoItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItem(int id){
        TodoItem todoItem = Storage.getAllTodoItems().get(id);
        if(todoItem == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.OK);
    }

    @PostMapping()
    public long add(@Valid @NotNull @RequestBody TodoItem item){
        return Storage.addTodoItem(item);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable("id") int id){
        Storage.removeTodoItem(id);
    }

    @PutMapping("/{id}")
    public  void update(@PathVariable("id") int id, String title){
        Storage.getItemById(id).setTitle(Objects.requireNonNull(title));
    }

}

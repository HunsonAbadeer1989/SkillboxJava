package main.controller;

import main.service.TodoService;
import main.model.TodoItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping(value = "/")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

//    @GetMapping
//    public Iterable<TodoItem> todoList(){
//        return todoService.todoList();
//    }

    @GetMapping("/index")
    public String todoList(Model model){
        model.addAttribute("today", DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(new Locale("ru"))
                .format(LocalDate.now()));

        model.addAttribute("listTodoItems", todoService.todoList());
        return "index";
    }

//    @PostMapping()
//    public int add(@Valid @NotNull @RequestBody TodoItem item){
//        return todoService.add(item);
//    }

    @GetMapping("/showAddItemForm")
    public String showAddItemForm(Model model){
        TodoItem newTodoItem = new TodoItem();
        model.addAttribute("todoItem", newTodoItem);
        return "new_item";
    }

    @PostMapping("/saveTodoItem")
    public String saveTodoItem(@ModelAttribute("todoItem") @Valid @NotNull @RequestBody TodoItem todoItem){
        todoService.add(todoItem);
        return "redirect:/index";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value="id") int id, Model model){
        Optional<TodoItem> todoItem = todoService.getTodoItemById(id);
        model.addAttribute("todoItem", todoItem.get());
        return "update_item";
    }

    @PutMapping(path ="/{id}")
    public String updateTodoItem(@PathVariable("id") int id, @Valid @NotNull @RequestBody TodoItem todoItem){
        todoService.update(id, todoItem);
        return "index";
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable int id){
        return ResponseEntity.of(todoService.getTodoItemById(id));
    }

    @GetMapping("/deleteTodoItem/{id}")
    public String deleteTodoItem(@PathVariable(value = "id") int id){
        this.todoService.deleteItem(id);
        return "redirect:/index";
    }

}
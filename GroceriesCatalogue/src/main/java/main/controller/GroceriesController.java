package main.controller;

import com.sun.istack.NotNull;
import main.model.GroceryItem;
import main.service.GroceryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class GroceriesController {

    private final GroceryService groceryService;

    public GroceriesController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("today", DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                .withLocale(new Locale(
                        "ru"))
                .format(LocalDate.now()));

        model.addAttribute("listGroceryItems", groceryService.getGroceryList());
        return "main";
    }

    @GetMapping("/showAddItemForm")
    public String showAddItemForm(Model model){
        GroceryItem item = new GroceryItem();
        model.addAttribute("groceryItem", item);
        return "new_item";
    }

    @PostMapping("/saveGroceryItem")
    public String putNewItem(@ModelAttribute("groceryItem") @Valid @NotNull @RequestBody GroceryItem groceryItem){
        groceryService.addItem(groceryItem);
        return "redirect:/";
    }

    @PutMapping(path = "/{id}")
    public String updateItem(@PathVariable(value="id") int id, @NotNull @Valid @RequestBody GroceryItem item){
        groceryService.updateItem(id, item);
        return "/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable int id, Model model){
        GroceryItem item = groceryService.getItemById(id).get();
        model.addAttribute("groceryItem", item);
        return "update_item";
    }

    @GetMapping("/deleteGroceryItem/{id}")
    public String deleteGroceryItem(@PathVariable(value="id") int id){
        groceryService.deleteItemById(id);
        return "redirect:/";
    }

}

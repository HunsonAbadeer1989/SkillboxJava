package main.service;

import main.dao.GroceryRepository;
import main.model.GroceryItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryService {

    private final GroceryRepository groceryRepo;

    public GroceryService(GroceryRepository groceryRepository, GroceryRepository groceryRepo) {
        this.groceryRepo = groceryRepo;
    }

    public List<GroceryItem> getGroceryList(){
        return groceryRepo.findAll();
    }

    public void addItem(GroceryItem groceryItem){
        groceryRepo.save(groceryItem);
    }

    public void deleteItemById(int id){
        groceryRepo.deleteById(id);
    }

    public void updateItem(int id, GroceryItem item){
        Optional<GroceryItem> groceryItem = groceryRepo.findById(id);
        groceryItem.ifPresent(value -> value.setDescription(item.getDescription()));
    }

    public Optional<GroceryItem> getItemById(int id){
        return groceryRepo.findById(id);
    }

}
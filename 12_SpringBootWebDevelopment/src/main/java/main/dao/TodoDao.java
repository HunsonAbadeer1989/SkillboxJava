package main.dao;

import main.model.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoDao extends CrudRepository<TodoItem, Integer> {

}

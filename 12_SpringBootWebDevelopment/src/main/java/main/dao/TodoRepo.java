package main.dao;

import main.model.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends CrudRepository<TodoItem, Integer> {

    TodoItem findById(int id);
}

package main.repo;

import main.model.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public interface TodoRepo extends CrudRepository<TodoItem, AtomicLong> {


}

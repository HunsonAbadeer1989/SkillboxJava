package main.service;

import main.dao.TodoRepo;
import main.model.TodoItem;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

class TodoServiceTest {
    private static final TodoItem TASK_A = new TodoItem(1, "Task A", false);

    TodoRepo mockRepository;
    TodoService service;

    @BeforeEach
    void setUp(){
        mockRepository = Mockito.mock(TodoRepo.class);
        service = new TodoService(mockRepository);
    }

    @Test
    void getTodoItemById_task_found(){
        given(mockRepository.findById(1))
                .willReturn(TASK_A);

        Assertions.assertTrue(service.getTodoItemById(1).isPresent());
    }

    @Test()
    void getTodoItemById_should_throw_exception(){ // NOT is capital to separate methods
        given(mockRepository.findById(anyInt()))
                .willThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> {
            service.getTodoItemById(anyInt());
        });
    }

    @Test
    void update_will_update_title(){
        given(mockRepository.findById(1))
                .willReturn(new TodoItem("Task A"));

        service.update(1, new TodoItem("Task B"));

        Assertions.assertNotEquals(TASK_A.getTitle(), "Task B");
    }

}
package main.service;

import main.dao.TodoRepo;
import main.model.TodoItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    void update_NotEquals(){
        given(mockRepository.findById(1)).willReturn(new TodoItem("Task A"));

        Assertions.assertTrue(service.getTodoItemById(1).isPresent());
        service.update(1, new TodoItem("Task B"));

        Assertions.assertNotEquals(TASK_A.getTitle(), service.getTodoItemById(1).get().getTitle());
        Assertions.assertNotEquals(TASK_A, service.getTodoItemById(1).get());

    }

}
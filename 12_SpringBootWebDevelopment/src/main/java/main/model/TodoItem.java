package main.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Table(name="todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @NotBlank
    @Column(name="title")
    private String title;

    @Column(name="status")
    private boolean done;

    public TodoItem() {
    }

    public TodoItem(@NotBlank String title) {
        this.title = title;
    }

    public TodoItem(int id, String name, boolean done) {
        this.id = id;
        this.title = name;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "'" + title + "'";
    }
}

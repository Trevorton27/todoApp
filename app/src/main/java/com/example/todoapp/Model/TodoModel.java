package com.example.todoapp.Model;

public class TodoModel {
    private int id, status;
    private String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTodo() {
        return task;
    }

    public void setTodo(String task) {
        this.task = task;
    }
}

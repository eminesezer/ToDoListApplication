package com.eminesezer.todolistapplicationbackend.model.todo;

import com.eminesezer.todolistapplicationbackend.enumaration.ToDoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ToDoItemRequest {
    private String todoName;
    private String todoDescription;
    private int userId;
    private Date deadline;
    private ToDoStatus todoStatus;
    private boolean isDependent;
    private int dependentTodos;

    public ToDoItemRequest(String todoName, String todoDescription, int userId, Date deadline, ToDoStatus todoStatus) {
        this.todoName = todoName;
        this.todoDescription = todoDescription;
        this.userId = userId;
        this.deadline = deadline;
        this.todoStatus = todoStatus;
    }

    public ToDoItemRequest() {
        super();
    }
}

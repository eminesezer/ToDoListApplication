package com.eminesezer.todolistapplicationbackend.entity;

import com.eminesezer.todolistapplicationbackend.enumaration.ToDoStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "todos")
@Builder(toBuilder = true)
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "todo_name")
    private String todoName;

    @Column(name = "todo_description")
    private String todoDescription;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "todo_list_id")
    private int todoListId;

    @Column(name = "insert_date")
    private Date insertDate;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "todo_status")
    private ToDoStatus todoStatus;

    @Column(name = "is_dependent")
    private boolean isDependent;

    @Column(name = "dependent_todos")
    private int dependentTodos;
}

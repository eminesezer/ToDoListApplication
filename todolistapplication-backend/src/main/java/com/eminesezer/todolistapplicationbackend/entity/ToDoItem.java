package com.eminesezer.todolistapplicationbackend.entity;

import com.eminesezer.todolistapplicationbackend.enumaration.ToDoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "todo_name", length = 100)
    private String todoName;

    @Column(name = "todo_description", length = 100)
    private String todoDescription;

    @Column(name = "user_id", length = 10)
    private int userId;

    @Column(name = "todo_list_id", length = 10)
    private int todoListId;

    @Column(name = "insert_date")
    private Date insertDate;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "todo_status")
    private ToDoStatus todoStatus;

    @Column(name = "is_dependent")
    private boolean isDependent;

    @Column(name = "dependent_todos", length = 10)
    private int dependentTodos;

}

package com.eminesezer.todolistapplicationbackend.repository;

import com.eminesezer.todolistapplicationbackend.entity.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoListRepository extends JpaRepository<ToDoList, Integer> {
    List<ToDoList> findToDoListsByUserId(int userId);

    ToDoList findToDoListByUserId(int userId);

    ToDoList findToDoListsByUserIdAndId(int userId, int id);
}
package com.eminesezer.todolistapplicationbackend.repository;

import com.eminesezer.todolistapplicationbackend.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {
    List<ToDoItem> findToDoItemsByUserIdAndTodoListId(int userId, int todoListId);

    ToDoItem findToDoItemByUserIdAndTodoListIdAndId(int userId, int toDoListId, int id);
    ToDoItem findToDoItemById(int id);

    List<ToDoItem> findToDoItemsByUserIdAndTodoListIdAndTodoStatusIn(int userid, int todoListId, List<String> status);

    void deleteToDoItemById(int id);

    void deleteToDoItemsByTodoListId(int listId);
}

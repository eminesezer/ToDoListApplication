package com.eminesezer.todolistapplicationbackend.controller;

import com.eminesezer.todolistapplicationbackend.entity.ToDoList;
import com.eminesezer.todolistapplicationbackend.repository.ToDoItemRepository;
import com.eminesezer.todolistapplicationbackend.repository.ToDoListRepository;
import com.eminesezer.todolistapplicationbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoListController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ToDoListRepository toDoListRepository;
    @Autowired
    ToDoItemRepository toDoItemRepository;

    @GetMapping("/{username}/todos")
    public List<ToDoList> getAllTodoLists(@PathVariable String username) {
        if (username.isEmpty()) {
            return null;
        }
        int userId = userRepository.findUserByUsername(username).getId();
        return toDoListRepository.findToDoListsByUserId(userId);
    }

    @GetMapping("/{username}/todos/{todo}")
    public ToDoList getTodoList(@PathVariable String username, @PathVariable int todo) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todo)) {
            return null;
        }
        int userId = userRepository.findUserByUsername(username).getId();
        return toDoListRepository.findToDoListsByUserIdAndId(userId, todo);
    }

    @DeleteMapping("/{username}/delete/{todo}")
    public ResponseEntity<Object> deleteTodoList(@PathVariable String username, @PathVariable int todo) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todo)) {
            return ResponseEntity.badRequest().body("Details for delete this todo list is not allowed.");
        }
        toDoListRepository.findToDoListsByUserId(todo);
        toDoItemRepository.deleteToDoItemById(todo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{username}/save/")
    public ResponseEntity<Void> saveToDoList(@PathVariable String username, @RequestBody String todoList) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todoList)) {
            return null;
        }
        int userId = userRepository.findUserByUsername(username).getId();
        ToDoList.builder().todoListName(todoList).userId(userId).build();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{username}/update/{todo}")
    public ResponseEntity<Void> updateTodoList(@PathVariable String username, @PathVariable int todo, @RequestBody String todoList) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todo) || ObjectUtils.isEmpty(todoList)) {
            return null;
        }
        ToDoList toDoItem = toDoListRepository.findToDoListByUserId(todo);
        toDoItem.setTodoListName(todoList);
        toDoListRepository.save(toDoItem);
        return ResponseEntity.noContent().build();
    }
}

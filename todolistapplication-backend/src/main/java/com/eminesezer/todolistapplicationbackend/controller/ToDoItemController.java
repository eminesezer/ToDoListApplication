package com.eminesezer.todolistapplicationbackend.controller;

import com.eminesezer.todolistapplicationbackend.entity.ToDoItem;
import com.eminesezer.todolistapplicationbackend.model.todo.ToDoItemRequest;
import com.eminesezer.todolistapplicationbackend.repository.ToDoItemRepository;
import com.eminesezer.todolistapplicationbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:30000")
public class ToDoItemController {
    @Autowired
    ToDoItemRepository toDoItemRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{username}/{todo}/todos")
    public List<ToDoItem> getAllTodoItemsOfList(@PathVariable String username, @PathVariable int todo) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todo)) {
            return null;
        }
        int userId = userRepository.findUserByUsername(username).getId();
        return toDoItemRepository.findToDoItemsByUserIdAndTodoListId(userId, todo);
    }

    @GetMapping("/{username}/{todo}/filter")
    public List<ToDoItem> getFilteredTodos(@PathVariable String username, @PathVariable int todo, @RequestBody List<String> status) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todo) || status.isEmpty()) {
            ResponseEntity.badRequest().body("Parameters are not filled.");
        }
        int userId = userRepository.findUserByUsername(username).getId();
        return toDoItemRepository.findToDoItemsByUserIdAndTodoListIdAndTodoStatusIn(userId, todo, status);
    }

    @PostMapping("/{username}/{todo}/delete/{id}")
    public ResponseEntity<Object> deleteTodoItem(@PathVariable String username, @PathVariable int todo, @PathVariable int id) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todo) || ObjectUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().body("Details for delete this todo is not allowed.");
        }
        ToDoItem todoWillBeDeleted = toDoItemRepository.findToDoItemById(id);
        if (todoWillBeDeleted.isDependent()) {
            return ResponseEntity.badRequest().body("This todo is deoendent to another");
        }
        toDoItemRepository.deleteToDoItemById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{username}/{todo}/save")
    public ResponseEntity<Void> saveToDoList(@PathVariable String username, @PathVariable int todo, @RequestBody ToDoItemRequest todoListItem) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todo) || ObjectUtils.isEmpty(todoListItem)) {
            return null;
        }
        int userId = userRepository.findUserByUsername(username).getId();
        ToDoItem newItem = ToDoItem.builder().todoName(todoListItem.getTodoName()).todoDescription(todoListItem.getTodoDescription()).userId(userId)
                .deadline(todoListItem.getDeadline()).todoStatus(todoListItem.getTodoStatus()).build();
        if (todoListItem.isDependent()) {
            newItem.setDependent(true);
            newItem.setDependentTodos(todoListItem.getDependentTodos());
        }
        toDoItemRepository.save(newItem);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{username}/{todo}/update/{id}")
    public ResponseEntity<Void> updateTodoItem(@PathVariable String username, @PathVariable int todo, @PathVariable int id, @RequestBody ToDoItemRequest updatedTodoVersion) {
        if (username.isEmpty() || ObjectUtils.isEmpty(todo) || ObjectUtils.isEmpty(id)) {
            return null;
        }
        ToDoItem toDoItem = toDoItemRepository.findToDoItemById(id);
        toDoItem.setTodoName(updatedTodoVersion.getTodoName());
        toDoItem.setTodoDescription(updatedTodoVersion.getTodoDescription());
        toDoItem.setUserId(updatedTodoVersion.getUserId());
        toDoItem.setDeadline(updatedTodoVersion.getDeadline());
        toDoItem.setTodoStatus(updatedTodoVersion.getTodoStatus());
        toDoItem.setDependent(updatedTodoVersion.isDependent());
        toDoItem.setDependentTodos(updatedTodoVersion.getDependentTodos());
        toDoItemRepository.save(toDoItem);

        return ResponseEntity.noContent().build();
    }
}
package com.eminesezer.todolistapplicationbackend.application;

import com.eminesezer.todolistapplicationbackend.entity.ToDoItem;
import com.eminesezer.todolistapplicationbackend.entity.ToDoList;
import com.eminesezer.todolistapplicationbackend.entity.User;
import com.eminesezer.todolistapplicationbackend.enumaration.AuthorityType;
import com.eminesezer.todolistapplicationbackend.enumaration.ToDoStatus;
import com.eminesezer.todolistapplicationbackend.repository.ToDoItemRepository;
import com.eminesezer.todolistapplicationbackend.repository.ToDoListRepository;
import com.eminesezer.todolistapplicationbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
class DataFiller {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ToDoListRepository toDoListRepository;
    @Autowired
    ToDoItemRepository toDoItemRepository;

    void dataFill() {
        addUsers();
        addTodoList();
        addTodoListItem();
    }

    void addUsers() {
        User adminUser = User.builder()
                .username("deneme")
                .password("$2a$10$bLV4avJSBdTaizX/Y7TUZO/8DOCA.HRzEJbm5ya3TLO.NwcFiVHZ6")
                .passwordPlaintext("Pa9F5Fa5bEvxVhd7")
                .isEnabled(true)
                .authorityType(AuthorityType.ADMIN)
                .build();

        User user = User.builder()
                .username("user")
                .password("$2a$10$bLV4avJSBdTaizX/Y7TUZO/8DOCA.HRzEJbm5ya3TLO.NwcFiVHZ6")
                .passwordPlaintext("Pa9F5Fa5bEvxVhd7")
                .isEnabled(true)
                .authorityType(AuthorityType.USER)
                .build();

        userRepository.save(adminUser);
        userRepository.save(user);
    }

    void addTodoList() {
        User user = userRepository.findUserByUsername("deneme");
        int userId = user.getId();
        ToDoList toDoList = ToDoList.builder()
                .userId(userId)
                .todoListName("DenemeList1").build();

        ToDoList toDoList2 = ToDoList.builder()
                .userId(userId)
                .todoListName("DenemeList22").build();

        ToDoList toDoList3 = ToDoList.builder()
                .userId(userId)
                .todoListName("DenemeList333").build();
        toDoListRepository.save(toDoList);
        toDoListRepository.save(toDoList2);
        toDoListRepository.save(toDoList3);

        userId = userRepository.findUserByUsername("user").getId();
        ToDoList toDoList5 = ToDoList.builder()
                .userId(userId)
                .todoListName("DenemeList5").build();

        ToDoList toDoList6 = ToDoList.builder()
                .userId(userId)
                .todoListName("DenemeList6").build();

        ToDoList toDoList7 = ToDoList.builder()
                .userId(userId)
                .todoListName("DenemeList7").build();
        toDoListRepository.save(toDoList5);
        toDoListRepository.save(toDoList6);
        toDoListRepository.save(toDoList7);
    }

    void addTodoListItem() {
        int userId = userRepository.findUserByUsername("deneme").getId();
        ToDoItem toDoItem = ToDoItem.builder()
                .todoName("TodoItem1")
                .insertDate(new Date(System.currentTimeMillis()))
                .isDependent(false)
                .todoStatus(ToDoStatus.TODO)
                .deadline(new Date(System.currentTimeMillis()))
                .userId(userId)
                .todoDescription("Description ... ")
                .build();
        ToDoItem toDoItem2 = ToDoItem.builder()
                .todoName("TodoItem2")
                .insertDate(new Date(System.currentTimeMillis()))
                .isDependent(false)
                .todoStatus(ToDoStatus.TODO)
                .deadline(new Date(System.currentTimeMillis()))
                .userId(userId)
                .todoDescription("Description ... ")
                .build();
        ToDoItem toDoItem3 = ToDoItem.builder()
                .todoName("TodoItem3")
                .insertDate(new Date(System.currentTimeMillis()))
                .isDependent(false)
                .todoStatus(ToDoStatus.TODO)
                .deadline(new Date(System.currentTimeMillis()))
                .userId(userId)
                .todoDescription("Description ... ")
                .build();
        toDoItemRepository.save(toDoItem);
        toDoItemRepository.save(toDoItem2);
        toDoItemRepository.save(toDoItem3);
    }

}

package com.eminesezer.todolistapplicationbackend.enumaration;

public enum ToDoStatus {
    COMPLETE("COMPLETE"),
    INPROGRASS("INPROGRASS"),
    TODO("TODO"),
    EXPIRED("EXPIRED");
    String name;

    ToDoStatus(String name) {
        this.name = name;
    }

    public static ToDoStatus getStatusByName(String name) {
        switch (name) {
            case "COMPLETE":
                return ToDoStatus.COMPLETE;
            case "INPROGRASS":
                return ToDoStatus.INPROGRASS;
            case "EXPIRED":
                return ToDoStatus.EXPIRED;
        }
        return ToDoStatus.TODO;
    }
}
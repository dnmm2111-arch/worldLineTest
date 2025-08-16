package com.example.worldlinetest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class User {

    private String user_name;
    private int task_id;
    private String task_list_name;
    private String task_name;
    private String  task_description;

    private int fk_task_id_parent;
    private int fk_task_id_child;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getTask_list_name() {
        return task_list_name;
    }

    public void setTask_list_name(String task_list_name) {
        this.task_list_name = task_list_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public Integer getFk_task_id_parent() {
        return fk_task_id_parent;
    }

    public void setFk_task_id_parent(int fk_task_id_parent) {
        this.fk_task_id_parent = fk_task_id_parent;
    }

    public int getFk_task_id_child() {
        return fk_task_id_child;
    }

    public void setFk_task_id_child(int fk_task_id_child) {
        this.fk_task_id_child = fk_task_id_child;
    }




}

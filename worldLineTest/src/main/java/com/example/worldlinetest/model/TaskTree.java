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
public class TaskTree {

    public void setFk_task_id_parent(int fk_task_id_parent) {
        this.fk_task_id_parent = fk_task_id_parent;
    }

    public void setFk_task_id_child(int fk_task_id_child) {
        this.fk_task_id_child = fk_task_id_child;
    }

    public int getFk_task_id_parent() {
        return fk_task_id_parent;
    }

    public int getFk_task_id_child() {
        return fk_task_id_child;
    }

    private int fk_task_id_parent;
    private int fk_task_id_child;
}

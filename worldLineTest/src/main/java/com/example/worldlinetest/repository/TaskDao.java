package com.example.worldlinetest.repository;

import com.example.worldlinetest.model.Task;
import com.example.worldlinetest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Component
public class TaskDao {

    JdbcTemplate jdbcTemplate;

    private final String SQL_ALL_LIST_TASK = "select user.name as user_name,\n" +
            "       task_list.name as task_list_name,\n" +
            "       task_tree.fk_task_id_parent,\n" +
            "       task_tree.fk_task_id_child,\n" +
            "       task.task_id,\n" +
            "       task.name as task_name,\n" +
            "       task.description \n" +
            "from  user\n" +
            "left join task_list on fk_user_id = user.user_id \n" +
            "left join task_tree on task_list.fk_task_id_parent = task_tree.fk_task_id_parent \n" +
            "left join task on task.task_id = task_tree.fk_task_id_child \n" +
            "and task_tree.fk_task_id_parent \n" +
            "where user_id = ? \t" +
            "order by task_list_name\t";
    private final String SQL_DELETE_TASK = "delete from task where task_id=?";
    private final String SQL_DELETE_TASK_LIST = "delete from task_list where fk_task_id_parent=?";
    private final String SQL_DELETE_TASK_TREE = "delete from task_tree where fk_task_id_parent=?";

    private final String SQL_UPDATE_TASK = "update task\n" +
            "set task_id=?, name=?, description=? \n" +
            "where task_id=?";

    private final String SQL_UPDATE_TASK_LIST  = "update task_list \n" +
            "set fk_task_id_parent=? \n" +
            "where fk_task_id_parent=?";


    private final String SQL_UPDATE_TASK_TREE  = "update task_tree \n" +
            "set fk_task_id_parent=? \n" +
            "where fk_task_id_parent=?";
    private final String SQL_INSERT_TASK_TREE = "insert into task_tree (fk_task_id_parent, fk_task_id_child) values (?, ?)";

    private final String SQL_INSERT_TASK_LIST = "insert into task_list " +
            " (fk_user_id, fk_task_id_parent, name) values (?, ?, ?)";
    @Autowired
    public TaskDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> getAllListsOfTasks(int userId) {
        List<User> users = jdbcTemplate.queryForStream(SQL_ALL_LIST_TASK, new UserMapper(), userId).toList();
        return users;
    }

    public Integer createTaskList(int userId, String name, Integer taskIdParent) {
        return jdbcTemplate.update(SQL_INSERT_TASK_LIST, userId, taskIdParent, name);
    }
    public Integer createTaskTree(int taskIdParent, int taskIdChild) {
        return jdbcTemplate.update(SQL_INSERT_TASK_TREE, taskIdParent, taskIdChild);
    }
    public Integer createTask(Task task) {
        SimpleJdbcInsert insertStatement = new SimpleJdbcInsert(jdbcTemplate).withTableName("task").usingGeneratedKeyColumns("task_id");

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", task.getName());
        parameters.put("description", task.getDescription());


        return insertStatement.executeAndReturnKey(parameters).intValue();
    }

    public Integer updateTask(Task task) {
        return jdbcTemplate.update(SQL_UPDATE_TASK, task.getId(), task.getName(), task.getDescription(), task.getId());
    }

    public Integer deleteTask(Integer taskId) {
        return jdbcTemplate.update(SQL_DELETE_TASK, taskId);
    }

    public Integer deleteTaskList(int taskIdParent) {
        int deletedTask = jdbcTemplate.update(SQL_DELETE_TASK_LIST, taskIdParent);
        int deletedTree = jdbcTemplate.update(SQL_DELETE_TASK_TREE, taskIdParent);
        return deletedTask + deletedTree;
    }

    public Integer moveTaskToTaskList(int newTaskIdParent, int oldTaskIdParent) {
        int deletedTask = jdbcTemplate.update(SQL_UPDATE_TASK_LIST, newTaskIdParent, oldTaskIdParent);
        int deletedTree = jdbcTemplate.update(SQL_UPDATE_TASK_TREE, newTaskIdParent, oldTaskIdParent);
        return deletedTask + deletedTree;
    }
}
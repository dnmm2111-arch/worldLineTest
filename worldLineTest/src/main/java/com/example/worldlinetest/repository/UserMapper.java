package com.example.worldlinetest.repository;

import com.example.worldlinetest.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();
        user.setUser_name(resultSet.getString("user_name"));
        user.setTask_list_name(resultSet.getString("task_list_name"));
        user.setFk_task_id_parent(resultSet.getInt("fk_task_id_parent"));
        user.setFk_task_id_child(resultSet.getInt("fk_task_id_child"));
        user.setTask_id(resultSet.getInt("task_id"));
        user.setTask_name(resultSet.getString("task_name"));
        user.setTask_description(resultSet.getString("description"));

        return user;
    }
}

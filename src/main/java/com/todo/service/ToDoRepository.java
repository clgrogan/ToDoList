package com.todo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Integer>{

	List<ToDo> findByUsername(String loggedInUserName);

}

package com.todo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.models.ToDo;

@Service
@SessionAttributes("name")
public class ToDoService {
	private static List<ToDo> todos = new ArrayList<>();
	private static int nextToDoCount = 1;

	static {
		todos.add(new ToDo(nextToDoCount++, "curtg", "Master Core Java", 
				LocalDate.now().plusDays(120), false));
		todos.add(new ToDo(nextToDoCount++, "curtg", "Spring Microservices", 
				LocalDate.now().plusDays(240), false));
		todos.add(new ToDo(nextToDoCount++, "curtg", "Culinary Arts", 
				LocalDate.now().plusDays(360), false));
	}
	
	public List<ToDo> getAll() {
		return todos;
	}
	public boolean addToDo(String username, String description, LocalDate targetDate, boolean done) {
		todos.add(new ToDo(nextToDoCount++, username, description, targetDate, done));
		return false;
	}
	public void deleteById(int id) {
		//		todos.removeIf(e -> e.getId() == id);
		Predicate<? super ToDo> predicate = e -> e.getId() == id;
		todos.removeIf(predicate);
	}
	public ToDo getById(int id) {
		ToDo todo = todos.stream()
				  .filter( e -> e.getId() == id)
				  .findAny()
				  .orElse(null);
		return todo;
	}
	public void updateToDo(ToDo todo) {
		System.out.println("updateItem(): "+todo);
		for (int i = 0; i < todos.size(); i++) {
			if(todo.getId()==todos.get(i).getId())todos.set(i, todo);
		}
	}

}

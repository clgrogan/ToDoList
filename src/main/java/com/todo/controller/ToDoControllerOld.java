package com.todo.controller;

import static com.todo.security.SecurityUtilities.getLoggedInUserName;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.model.ToDo;
import com.todo.service.ToDoService;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class ToDoControllerOld {

	private ToDoService toDoService;

	public ToDoControllerOld(ToDoService toDoService) {
		super();
		this.toDoService = toDoService;
	}

	@GetMapping("todo-list")
	public String listAllToDos(ModelMap model) {
		List<ToDo> todos = toDoService.findByUsername(getLoggedInUserName());
		model.addAttribute("todos", todos);
		return "listToDos";
	}

	@GetMapping("add-todo")
	public String showNewToDoPage(ModelMap model) {
		System.out.println("\n\npublic String showNewToDoPage(ModelMap model)\n\n");
		ToDo todo = new ToDo(0, getLoggedInUserName(), "Default Description", LocalDate.now().plusDays(90), false);
		model.put("todo", todo);
		return "todo";
	}

	@PostMapping("add-todo")
	public String addNewToDoItem(ModelMap model, @Valid ToDo todo, BindingResult result) {
		this.toDoService.addToDo(getLoggedInUserName(), todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:todo-list";
	}

	@GetMapping("delete-todo")
	public String deleteToDo(@RequestParam int id) {
		this.toDoService.deleteById(id);
		return "redirect:todo-list";
	}

	@GetMapping("update-todo")
	public String updateToDo(@RequestParam int id, ModelMap model) {
		ToDo todo = toDoService.getById(id);
		model.put("todo", todo);
		toDoService.updateToDo(todo);
		return "todo";
	}

	@PostMapping("update-todo")
	public String updateToDoItem(ModelMap model, @Valid ToDo todo, BindingResult result) {
		todo.setUsername(getLoggedInUserName());
		toDoService.updateToDo(todo);
		return "redirect:todo-list";
	}

}

package com.todo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.models.ToDo;
import com.todo.service.ToDoService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class ToDoController {

	private ToDoService toDoService;

	public ToDoController(ToDoService toDoService) {
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
//		if(result.hasErrors()) {
//			model.put("todo", todo);
//			return "todo";
//		}
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
//		if(result.hasErrors()) {
//			model.put("todo", todo);
//			return "todo";
//		}
		todo.setUsername(getLoggedInUserName());
		toDoService.updateToDo(todo);
		return "redirect:todo-list";
	}

	private String getLoggedInUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}

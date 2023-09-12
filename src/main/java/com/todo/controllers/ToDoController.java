package com.todo.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.todo.models.ToDo;
import com.todo.services.ToDoService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class ToDoController {

	private ToDoService toDoService;

	public ToDoController(ToDoService toDoService) {
		super();
		this.toDoService = toDoService;
	}

	@RequestMapping("todo-list")
	public String listAllToDos(ModelMap model) {
		List<ToDo> todos = toDoService.getAll();
		model.addAttribute("todos", todos);
		return "listToDos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewToDoPage(ModelMap model) {
		System.out.println("\n\npublic String showNewToDoPage(ModelMap model)\n\n");
		ToDo todo = new ToDo(0, (String) model.get("name"), "Default Description", LocalDate.now().plusDays(90), false);
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewToDoItem(ModelMap model, @Valid ToDo todo, BindingResult result) {
//		if(result.hasErrors()) {
//			model.put("todo", todo);
//			return "todo";
//		}
		this.toDoService.addToDo((String) model.get("name"), todo.getDescription(), todo.getTargetDate(),
				false);
		return "redirect:todo-list";
	}

	@RequestMapping("delete-todo")
	public String deleteToDo(@RequestParam int id) {
		this.toDoService.deleteById(id);
		return "redirect:todo-list";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String updateToDo(@RequestParam int id, ModelMap model) {
		ToDo todo = toDoService.getById(id);
		model.put("todo", todo);
		System.out.println(todo);
		toDoService.updateToDo(todo);
		return "todo";
	}

	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String updateToDoItem(ModelMap model, @Valid ToDo todo, BindingResult result) {
//		if(result.hasErrors()) {
//			model.put("todo", todo);
//			return "todo";
//		}
		todo.setUsername((String)model.get("name"));
		toDoService.updateToDo(todo);
//		this.toDoService.addToDo((String) model.get("name"), todo.getDescription(), LocalDate.now().plusDays(90),
//				false);
		return "redirect:todo-list";
	}

}

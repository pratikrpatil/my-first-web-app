package com.webapp.firstwebapp.myfirstwebapp.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.webapp.firstwebapp.myfirstwebapp.repositories.TodoRepository;
import com.webapp.firstwebapp.myfirstwebapp.todo.Todo;
import com.webapp.firstwebapp.myfirstwebapp.todo.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;

	@RequestMapping("list-todos")
	public String listTodos(ModelMap model)
	{
		String username=getLoggedinUserDetails();
		List<Todo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos",todos);
		return "list-todos";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model)
	{
		String username=getLoggedinUserDetails();
		Todo todo = new Todo(0,username,"",LocalDate.now().plusDays(7),false);
		model.put("todo",todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result)
	{
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedinUserDetails();
		todo.setUsername(username);
		todoRepository.save(todo);
		
//		todoService.addNewTodo(getLoggedinUserDetails(),
//		todo.getDescription(),todo.getTargetDate(), false);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id)
	{
		todoRepository.deleteById(id);
		//todoService.deleteTodo(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model)
	{
		Todo todo = todoRepository.findById(id).get();
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method = RequestMethod.POST)
	public String UpdateTodo(ModelMap model, @Valid Todo todo, BindingResult result)
	{
		if(result.hasErrors()) {
			return "todo";
		}
		String username = getLoggedinUserDetails();
		todo.setUsername(username);
		todoRepository.save(todo);
		//todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
	private String getLoggedinUserDetails() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
	}
}

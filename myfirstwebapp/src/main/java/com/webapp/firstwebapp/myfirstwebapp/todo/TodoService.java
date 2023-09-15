package com.webapp.firstwebapp.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	private static List<Todo> todoList = new ArrayList<>();
	
	private static int count = 0;
	static {
		todoList.add(new Todo(++count,"pratik",
				"Learn Spring Boot",
				LocalDate.now().plusDays(25),
				false));
		todoList.add(new Todo(++count,"Dipak",
				"Learn Spring Boot In Depth",
				LocalDate.now().plusDays(24),
				false));
	}
	
	public List<Todo> findByUserName(String username){
		
		Predicate<? super Todo> predicate = todo->todo.getUsername() == username;
		return todoList.stream().filter(predicate).toList();
	}
	
	public void addNewTodo(String username, String description, LocalDate now, boolean done)
	{
		Todo todo = new Todo(++count, username, description, now, done);
		todoList.add(todo);	
	}
	
	public void deleteTodo(int id) {
		
		Predicate<? super Todo> predicate = todo->todo.getId() == id;
		todoList.removeIf(predicate);
	}

	public Todo findById(int id) {

		Predicate<? super Todo> predicate = todo->todo.getId() == id;
		
		Todo todo = todoList.stream().filter(predicate).findFirst().get();
		
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {

		deleteTodo(todo.getId());
		todoList.add(todo);
	}

}

package com.webapp.firstwebapp.myfirstwebapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.firstwebapp.myfirstwebapp.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

	public List<Todo> findByUsername(String username);
}

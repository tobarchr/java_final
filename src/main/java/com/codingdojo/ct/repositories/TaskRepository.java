package com.codingdojo.ct.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.ct.models.Task;

@Repository
public interface TaskRepository extends CrudRepository <Task, Long>{
	
	List<Task> findAll();
	
}

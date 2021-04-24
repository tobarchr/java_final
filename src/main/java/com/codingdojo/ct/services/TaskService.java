package com.codingdojo.ct.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.ct.models.Task;
import com.codingdojo.ct.repositories.TaskRepository;

@Service
public class TaskService {
	private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	 public List<Task> allTasks() {
	        return taskRepository.findAll();
	    }

	 	public Task createTask(Task t) {
	        return taskRepository.save(t);
	    }
	 	
	 	public Task updateTask(Task t) {
	 		return taskRepository.save(t);
	 	}

	    
	    public Task findTask(Long id) {
	        Optional<Task> optionalTask = taskRepository.findById(id);
	        if(optionalTask.isPresent()) {
	            return optionalTask.get();
	        } else {
	            return null;
	        }
	    }
	    
	    public void deleteTask(Long id) {
	    	taskRepository.deleteById(id);
	    }
}

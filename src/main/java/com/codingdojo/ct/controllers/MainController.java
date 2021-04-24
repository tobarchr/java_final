package com.codingdojo.ct.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.ct.models.Task;
import com.codingdojo.ct.models.User;
import com.codingdojo.ct.services.TaskService;
import com.codingdojo.ct.services.UserService;
import com.codingdojo.ct.validator.UserValidator;

@Controller
public class MainController {
	private final UserService userService;
    private final UserValidator userValidator;
    private final TaskService taskService;

	
	public MainController(UserService userService, UserValidator userValidator, TaskService taskService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.taskService = taskService;
	}
	
	//--------------------------------------------------------------------------------------------------
	//	User Registration / Login routes
	//--------------------------------------------------------------------------------------------------
	
	@RequestMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "dashboard.jsp";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email,
							@RequestParam("password") String password,
							HttpSession session,
							RedirectAttributes redirect ) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if(isAuthenticated) {
			
			User u = userService.findByEmail(email);
			session.setAttribute("user_id",u.getId());
			return "redirect:/tasks";
		}
		
		else {
			redirect.addFlashAttribute("error","Invalid credentials, try again!");
			return "redirect:/";
		}		
	}
	
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user,
								BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
	        if(result.hasErrors()) {
	            return "dashboard.jsp";
	        }
	        User u = userService.registerUser(user);
	        session.setAttribute("user_id", u.getId());
	        return "redirect:/tasks";
	}
	
	
	//--------------------------------------------------------------------------------------------------
	//	View All Tasks
	//--------------------------------------------------------------------------------------------------
	
	@RequestMapping("/tasks")
	public String viewAllTasks(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		}
        else {
		User currentUser = userService.findUserById(userId);
        List<Task> tasks = taskService.allTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("currentUser",currentUser);
        return "tasks.jsp";
        }
    }
	
	//--------------------------------------------------------------------------------------------------
	//	Create New Tasks
	//--------------------------------------------------------------------------------------------------
		
	
	@RequestMapping("/tasks/new")
	  public String newTask(Model model,HttpSession session) {

		//Convert Session User Id into long to gather list of assignees
        Long currentUser = (Long) session.getAttribute("user_id");
        if(currentUser == null) {
			return "redirect:/";
		}
        List<User> assignees = userService.otherUsers(currentUser);
        model.addAttribute("user", currentUser);
		model.addAttribute("assignee",assignees);
		model.addAttribute("task",new Task());
        return "new.jsp";
    }
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
		public String saveTask(@Valid @ModelAttribute ("task") Task task, 
								BindingResult result, Model model, HttpSession session) {

        List<User> assignees = userService.otherUsers( (Long) session.getAttribute("user_id"));
		model.addAttribute("task",task);
		model.addAttribute("assignee",assignees);
		 if (result.hasErrors()) {
	            return "new.jsp";
	        } else {
	            taskService.createTask(task);
	            return "redirect:/tasks";
	        }
	}
	
	
    //--------------------------------------------------------------------------------------------------
  	//	Edit the Task model record
  	//--------------------------------------------------------------------------------------------------
	
	 @RequestMapping("/tasks/{id}/edit")
	    public String editTask(@PathVariable("id") Long id, 
	    		Model model,
	    		HttpSession session) {
	        Long userId = (Long) session.getAttribute("user_id");
		 	List <User> u = userService.otherUsers(userId);
	        Task task = taskService.findTask(id);
	        model.addAttribute("task", task);
	        model.addAttribute("assignee",u);
	        return "edit.jsp";
	    }

	
	@RequestMapping(value="/tasks/{id}", method=RequestMethod.PUT)
        public String update(@Valid @ModelAttribute("task") Task task, 
        		BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
        List<User> assignees = userService.otherUsers(userId);
        model.addAttribute("assignee",assignees);
            if (result.hasErrors()) {
                return "edit.jsp";
            } else {
            	taskService.updateTask(task);
            	return "redirect:/tasks";
            }
        }
    
    //--------------------------------------------------------------------------------------------------
  	//	View Model using Task ID
  	//--------------------------------------------------------------------------------------------------
    
    @RequestMapping("/tasks/{id}")
    	public String viewTask(@PathVariable("id") Long id, Model model, HttpSession session) {
    	Task task = taskService.findTask(id);
        model.addAttribute("task", task);
        model.addAttribute("user_id",session.getAttribute("user_id"));
    	return "view.jsp"; 
    	
    }
    

	//--------------------------------------------------------------------------------------------------
	//	Delete a Tasks
	//--------------------------------------------------------------------------------------------------
			
    
    @RequestMapping(value="/tasks/{id}", method=RequestMethod.DELETE)
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
    
    
    //--------------------------------------------------------------------------------------------------
  	//	Logout of session
  	//--------------------------------------------------------------------------------------------------
  	
	@RequestMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}
	
	
}

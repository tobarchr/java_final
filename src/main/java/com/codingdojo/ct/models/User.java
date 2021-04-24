package com.codingdojo.ct.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	 	@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	 	@Column
	 	@NotNull(message="Field cannot be empty")
	 	@Email(message="Input a valid email address")
	    private String email;
	 	@Column
	 	@Size(min=3,max=75,message="Field must be 3 characters long")
		private String name;
	 	@Column
	 	@Size(min=8, message="Password must be greater than 8 characters")
	    private String password;
	 	@Column
		@NotBlank (message = "Field cannot be empty")
	 	@Transient
	    private String passwordConfirmation;
	    @Column(updatable=false)
		@DateTimeFormat(pattern = "MM/dd/yyyy")
	    private Date createdAt;
	    @Column
		@DateTimeFormat(pattern = "MM/dd/yyyy")
	    private Date updatedAt;

	    @OneToMany(mappedBy="creator",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Task> createdTask;
	    
	    @OneToMany(mappedBy="assignee",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Task> assignedTask;
	    
	    
	    public User() {
	    }
	    
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}
		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
		}
		public Date getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}
		public Date getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Task> getCreatedTask() {
			return createdTask;
		}

		public void setCreatedTask(List<Task> createdTask) {
			this.createdTask = createdTask;
		}

		public List<Task> getAssignedTask() {
			return assignedTask;
		}

		public void setAssignedTask(List<Task> assignedTask) {
			this.assignedTask = assignedTask;
		}

		
}

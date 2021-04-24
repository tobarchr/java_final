<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <title>Task Manager</title>
</head>
<body>
    <div class="container mx-auto p-4 bg-light text-dark">
        <div class="row mt-4">
            <div class="col-5">
                <h1>Register</h1>
                <form:form method="POST" action="/register" modelAttribute="user">
                    <div class="form-group mb-3">
                        <form:label path="name" class="form-label">Name:</form:label>
                        <form:errors path="name" class="form-text text-danger"></form:errors>
                        <form:input type="name" path="name" class="form-control"/>
                    </div>
                    <div class="form-group mb-3">
                        <form:label path="email" class="form-label">Email:</form:label>
                        <form:errors path="email" class="form-text text-danger"></form:errors>
                        <form:input type="email" path="email" class="form-control"/>
                    </div>
                    <div class="form-group mb-3">
                        <form:label path="password" class="form-label">Password:</form:label>
                        <form:errors path="password" class="form-text text-danger"></form:errors>
                        <form:password path="password" class="form-control"/>
                    </div>
                    <div class="form-group mb-3">
                        <form:label path="passwordConfirmation" class="form-label">Password Confirmation:</form:label>
                        <form:errors path="passwordConfirmation" class="form-text text-danger"></form:errors>
                        <form:password path="passwordConfirmation" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Register</button>
                </form:form>
            </div>
            <div class="col-2"></div>
            <div class="col-5">
                <h1>Login</h1>
                <p class="text-danger"><c:out value="${error}"/></p>
                <form method="post" action="/login">
                    <div class="mb-3">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="text" id="email" name="email" class="form-control"/>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" id="password" name="password" class="form-control"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>  
            </div>
        </div>
    </div>
</body>
</html>
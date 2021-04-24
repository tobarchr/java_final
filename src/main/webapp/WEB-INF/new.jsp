<%@ page isErrorPage="true" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
    crossorigin="anonymous">
    <title>New Task</title>
</head>
<body>
    <div class="container mx-auto mt-5  p-4 bg-light text-dark" style="width: 600px;">
        <div class="row">
            <h1>New Task</h1>

        </div>
        <div class="row">
                    <form:form action="/create" method="post" modelAttribute="task">
                    <form:input type="hidden" path="creator" value="${user}"/>
                <div class="form-group mb-3">
                    <form:label path="name" class="form-label">Task:</form:label>
                    <form:errors path="name" class="form-text"></form:errors>
                    <form:input path="name" class="form-control"></form:input>
                </div>
                <div class="form-group mb-3">
                    <form:label path="assignee" class="form-label">Assignee:</form:label>
                    <form:errors path="assignee" class="form-text"></form:errors>
                    <form:select path="assignee" class="form-select">
                        <c:forEach items="${assignee}" var="a">
                            <form:option value="${a.id}">${a.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="form-group mb-3">
                    <form:label path="priority" class="form-label">Priority:</form:label>
                    <form:errors path="priority" class="form-text"></form:errors>
                    <form:select path="priority" class="form-select">
                        <form:option value="High">High</form:option>
                        <form:option value="Medium">Medium</form:option>
                        <form:option value="Low">Low</form:option>
                    </form:select>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
            </form:form>
        </div>
            </div>
        </div>
    </div>
</body>
</html>
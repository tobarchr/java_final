<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
    crossorigin="anonymous">
    <title>Task Details</title>
</head>
<body>
    <div class="container mt-4  p-4 bg-light text-dark">
        <div class="col-6">
            <div class="row mt-4">
                <h1>Task : <c:out value="${task.name}"/></h1>
            </div>
            <div class="row mt-4">
                <p>Creator  : <c:out value="${task.creator.name}"/></p>
                <p>Assignee : <c:out value="${task.assignee.name}"/></p>
                <p>Priority : <c:out value="${task.priority}"/></p>
            </div>
            <div class="row mt-4">
                <div class="col-2">
                    <c:choose>
                    <c:when test="${task.creator.id == user_id}">
                        <a href="/tasks/${task.id}/edit" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Edit</a>
                    </c:when>
                    </c:choose>
                </div>
                <div class="col-1"></div>
                <c:choose>
                <c:when test="${task.creator.id == user_id}">
                    <div class="col-3">
                        <form action="/tasks/${task.id}" method="post">
                            <input type="hidden" name="_method" value="delete">
                            <input type="submit" value="Delete" class="btn btn-danger">
                        </form>
                    </div>    
                </c:when>
                <c:when test="${task.assignee.id == user_id}">
                        <form action="/tasks/${task.id}" method="post">
                            <input type="hidden" name="_method" value="delete">
                            <input type="submit" value="Complete" class="btn btn-danger">
                        </form>
                </c:when>
                <c:otherwise>
            </c:otherwise>
            </c:choose>
            </div>
        </div>
        <div class="col-6"></div>
    </div>
</body>
</html>
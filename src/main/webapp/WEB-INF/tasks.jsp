<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <title>All Tasks</title>
</head>
<body>
    <div class="container mt-4  p-4 bg-light text-dark">
        <div class="row">
            <div class="col-5">
                <h1>Welcome, ${currentUser.name}</h1>

            </div>
            <div class="col-2">

            </div>
            <div class="col-3">
                <a href="/tasks/new" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">New Task</a>
            </div>
            <div class="col-2">
                <a href="/logout">Logout</a>
            </div>
        </div>
    <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">Task</th>
            <th scope="col">Creator</th>
            <th scope="col">Assignee</th>
            <th scope="col">Priority</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${tasks}" var="t">
                <tr>
                    <td><a href="/tasks/${t.id}"><c:out value="${t.name}"/></a></td>
                    <td><c:out value="${t.creator.name}"/></td>
                    <td><c:out value="${t.assignee.name}"/></td>
                    <td><c:out value="${t.priority}"/></td>
                </tr>
            </c:forEach>
        </tbody>
      </table>
    </div>
</body>
</html>
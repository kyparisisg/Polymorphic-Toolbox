<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="user" class="com.temple.polymorphic.toolbox.dto.UserDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Getting Started: Handling Form Submission</title>		     <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		     <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>All Employees</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>

<body>
<h1>Results in Users table</h1>
<div class="container my-2">

    <div th:case="*">
        <table class="table table-striped table-responsive-md">
            <thead>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>IP</th>
                <th>Port</th>
                <th>Def. User</th>
                <th>Health</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>

            <td th:text>${id}</td>
            <td th:text >${ip}</td>
            <td th:text >${username}</td>
            <td th:text >${name}</td>
            <td th:text >${port}</td>
            <td th:text >${health}</td>
            <%--            <td><form:form method="GET" action="/api/users/update/${id}"><input type="submit" value="submit"></form:form></td>--%>
            <td><form:form method="GET" action="/api/servers/update"><input type="submit" value="Edit"></form:form></td>
            <td><form:form method="GET" action="/api/servers/delete"><input type="submit" value="Delete"></form:form></td>

            <td th:text></td>

            </tr>
            <tr>
                <td>Return to:</td>
                <td><a href="/api/servers/">Manage Servers</a></td>
            </tr>

            </tbody>
        </table>
    </div>

</div>
</div>
</div>
</div>
</div>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="server" class="com.temple.polymorphic.toolbox.dto.ServerDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>All Servers</title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>All Servers</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>

<body>
<h1>User's Permissions table</h1>
<div class="container my-2">
    <td><form:form name="add" method="GET" action="/api/users/permissions/add/${email}"><input type="submit" value="Add Permission"></form:form></td>
    <div th:case="*">
        <table class="table table-striped table-responsive-md">
            <thead>
            <tr>
                <th>User Name</th>
                <th>Server Name</th>
                <th>Creation Date</th>
                <th>Username</th>
                <th>Password</th>
                <th>Valid</th>
                <th>Delete</th>
            </tr>
            </thead>
            <c:forEach items="${perms}" var="perm">
                <tr>
                    <td>${perm.user}</td>
                    <td>${perm.server}</td>
                    <td>${perm.creationDate}</td>
                    <td>${perm.usernameCred}</td>
                    <td>${perm.passwordCred}</td>
                    <td>${perm.valid}</td>
                    <td><form:form name="delete" method="POST" action="/api/users/permissions/delete/${perm.user.id}+${perm.server.id}"><input type="submit" value="Delete"></form:form></td>

                </tr>
            </c:forEach>
            </tr>
        </table>
    </div>
    <tr>
        <td>Return to :</td>
        <td><a href="/api/users/">Manage Users</a></td>
    </tr>

</div>
</body>
</html>
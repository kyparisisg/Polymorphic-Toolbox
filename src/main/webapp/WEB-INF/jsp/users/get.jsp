<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.temple.polymorphic.toolbox.dto.UserDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>All Users</title>		     <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		     <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>All Users</title>
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
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Edit</th>
                <th>Permissions</th>
                <th>Delete</th>
            </tr>
            </thead>
            <c:forEach items="${list}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td><form:form name="edit" method="GET" action="/api/users/update/${user.email}"><input type="submit" value="Edit"></form:form></td>
                    <td><form:form name="permissions" method="GET" action="/api/users/permissions/get/${user.email}"><input type="submit" value="Permissions"></form:form></td>
                    <td><form:form name="delete" method="GET" action="/api/users/delete/${user.email}"><input type="submit" value="Delete"></form:form></td>
                </tr>
            </c:forEach>
            </tr>
        </table>
        <table class="table table-striped table-responsive-md">
            <thead>
            <tr>
                <<th>User Email</th>
                <th>Server Name</th>
                <th>Creation Date</th>
                <th>Username</th>
                <th>Password</th>
                <th>Valid</th>
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
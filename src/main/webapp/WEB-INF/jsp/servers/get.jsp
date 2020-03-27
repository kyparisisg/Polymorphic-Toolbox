<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="server" class="com.temple.polymorphic.toolbox.dto.ServerDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>All Servers</title>		     <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		     <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>All Servers</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>

<body>
<h1>Results in Servers table</h1>
<div class="container my-2">

    <div th:case="*">
        <table class="table table-striped table-responsive-md">
            <thead>
            <tr>
                <th>ID</th>
                <th>Hostname</th>
                <th>IPv4</th>
                <th>Port</th>
                <th>Admin User</th>
                <th>Health</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <c:forEach items="${list}" var="server">
                <tr>
                    <td>${server.id}</td>
                    <td>${server.name}</td>
                    <td>${server.ip}</td>
                    <td>${server.port}</td>
                    <td>${server.usernameCred}</td>
                    <td>${server.health}</td>
                    <td><form:form name="edit" method="GET" action="/api/servers/update/${server.ip}"><input type="submit" value="Edit"></form:form></td>
                    <td><form:form name="delete" method="GET" action="/api/servers/delete/${server.ip}"><input type="submit" value="Delete"></form:form></td>
                </tr>
            </c:forEach>
            </tr>
        </table>
    </div>
    <tr>
        <td>Return to :</td>
        <td><a href="/api/servers/">Manage Servers</a></td>
    </tr>

</div>
</body>
</html>
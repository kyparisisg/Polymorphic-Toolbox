<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to Spring Web MVC project</title>
</head>

<body>
<h1>All Users</h1>
<p>----------------------------------------------</p>
<p>----------------------------------------------</p>
<p>----------------------------------------------</p>

</body>
<ul>
    <c:forEach var="userDto" items="${list}">
        <p>User ID: <b>${userDto.id}</b></p>
        <div>
            <p>First Name: <b>${userDto.firstName}</b></p>
            <p>Last Name: <b>${userDto.lastName}</b></p>
            <p>Email: <b>${userDto.email}</b></p>
            <p>Role: <b>${userDto.role}</b></p>
        </div>
        <p>----------------------------------------------</p>
    </c:forEach>
</ul>
<p><a href="/api/users/update">Edit User</a></p>
<p><a href="/api/users">Return to Manage Users</a></p>
<p><a href="/api/admin">Return to Admin Dashboard</a></p>

</html>
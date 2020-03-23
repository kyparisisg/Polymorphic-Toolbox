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
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>

                    <td th:text>${firstName}</td>
                    <td th:text >${lastName}</td>
                    <td th:text >${email}</td>
                    <td th:text >${role}</td>
        <%--            <td><form:form method="GET" action="/api/users/update/${id}"><input type="submit" value="submit"></form:form></td>--%>
                    <td><form:form name="edit" method="GET" action="/api/users/update/${email}"><input type="submit" value="Edit"></form:form></td>
                    <td><form:form name="delete" method="GET" action="/api/users/delete/${email}"><input type="submit" value="Delete"></form:form></td>

                    <td th:text></td>

                    </tbody>
                </table>
            </div>
            <tr>
                <td>Return to :</td>
                <td><a href="/api/users/">Manage Users</a></td>
            </tr>

    </div>

</body>
</html>

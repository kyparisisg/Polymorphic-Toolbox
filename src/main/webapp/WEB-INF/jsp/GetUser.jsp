<jsp:useBean id="user" class="com.temple.polymorphic.toolbox.dto.UserDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>		 <head>
    <title>Getting Started: Handling Form Submission</title>		     <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />		     <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>All Employees</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>		 </head>

<body>		 <body>
<h1>Form</h1>		 <div class="container my-2">
    <form  action = "#" th:action="@{/users}" th:object="${user}" method="post">		     <div class="card">
        <%--    <p>Id: <input type="text" th:field="*{id}" /></p>--%>		         <div class="card-body">
        <p>Role: <input type="text" th:field="*{role}" /></p>		          <%-- GIANNIS 4 PM   <div th:switch="${user}" class="container my-5"> --%>
        <p>FirstName: <input type="text" th:field="*{firstName}" /></p>		 <%--                <p class="my-5">--%>
        <p>LastName: <input type="text" th:field="*{lastName}" /></p>		 <%--                    <a href="/edit" class="btn btn-primary">--%>
        <p>Email: <input type="text" th:field="*{email}" /></p>		 <%--                        <i class="fas fa-user-plus ml-2"> Add Employee </i></a>--%>

        <%--                </p>--%>
        <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>		                 <div class="col-md-10"/></div>
    </form>
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

            <%--                            <p>${users[0]}</p>--%>
            <%--                            <th:block th:each="user : ${users}">--%>
            <%--                            <tr th:block th:each="user : ${users}" >--%>
            <td th:text>${firstName}</td>
            <td th:text >${lastName}</td>
            <td th:text >${email}</td>
            <td th:text >${role}</td>
            <td th:text></td>
            <%--                                <td>--%>
            <%--                                    <a th:href="@{/edit/{id}(id=${employee.id})}"--%>
            <%--                                       class="btn btn-primary">--%>
            <%--                                        <i class="fas fa-user-edit ml-2"></i>--%>
            <%--                                    </a>--%>
            <%--                                </td>--%>
            <%--                                <td>--%>
            <%--                                    <a th:href="@{/delete/{id}(id=${employee.id})}"--%>
            <%--                                       class="btn btn-primary">--%>
            <%--                                        <i class="fas fa-user-times ml-2"></i>--%>
            <%--                                    </a>--%>
            <%--                                </td>--%>
            </tr>
            <%--                            </th:block>--%>

            </tbody>
        </table>
    </div>

</div>
</div>
</div>
</div>
</div>
</body>		 </body>
</html>
</html>
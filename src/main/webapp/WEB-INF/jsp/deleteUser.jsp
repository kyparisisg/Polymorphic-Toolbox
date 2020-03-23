<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Manage User - Delete Operation</title>
</head>

<body>
<h2>Delete User</h2>
<form:form method = "POST" action = "/api/users/delete">
    <table>
        <tr>
            <td><form:label path = "email">Email</form:label></td>
            <td><form:input path = "email" value="${email}"/></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Delete"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>
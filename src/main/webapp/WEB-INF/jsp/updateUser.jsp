<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>

<body>
<h2>User Update Information</h2>
<h3>(email must be the same as one in the db to update the rest information)</h3>
<form:form method = "POST" action = "/api/users/update">
    <table>
        <tr>
            <td><form:label path = "firstName">First Name</form:label></td>
            <td><form:input path = "firstName" /></td>
        </tr>
        <tr>
            <td><form:label path = "lastName">Last Name</form:label></td>
            <td><form:input path = "lastName" /></td>
        </tr>
        <tr>
            <td><form:label path = "email">Email</form:label></td>
            <td><form:input path = "email" /></td>
        </tr>
        <tr>
            <td><form:label path = "role">Role</form:label></td>
            <td><form:input path = "role" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>
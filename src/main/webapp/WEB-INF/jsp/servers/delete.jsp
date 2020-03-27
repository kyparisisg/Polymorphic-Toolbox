<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Manage User - Delete Operation</title>
</head>

<body>
<h2>Delete User</h2>
<form:form method = "POST" action = "/api/servers/delete">
    <table>
        <tr>
            <td><form:label path = "ip">IPv4 Address</form:label></td>
            <td><form:input path = "ip" value="${ip}"/></td>
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
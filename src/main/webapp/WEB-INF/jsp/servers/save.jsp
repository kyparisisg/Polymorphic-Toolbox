<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Add Server</title>
</head>

<body>
<h2>Server Information</h2>
<form:form method = "POST" action = "/api/servers/save">
    <table>
        <tr>
            <td><form:label path = "name">Hostname:</form:label></td>
            <td><form:input path = "name" /></td>
        </tr>
        <tr>
            <td><form:label path = "ip">IPv4 Address:</form:label></td>
            <td><form:input path = "ip" /></td>
        </tr>
        <tr>
            <td><form:label path = "port">Port:</form:label></td>
            <td><form:input path = "port" value="22"/><small> <b>Hint:</b> If configured otherwise please <u><i>correct the value</i></u>.</small></td>
        </tr>
        <tr>
            <td><form:label path = "usernameCred">Admin User:</form:label></td>
            <td><form:input path = "usernameCred" /></td>
        </tr>
        <tr>
            <td><form:label path = "passwordCred">Admin Password:</form:label></td>
            <td><form:input path = "passwordCred" type = "password"/></td>
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
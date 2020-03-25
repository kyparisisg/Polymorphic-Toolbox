<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Update Server</title>
</head>

<body>
<h2>Server Update Information</h2>
<h3>(IPv4 must be the same as one in the db to update the rest information)</h3>
<form:form method = "POST" action = "/api/servers/update">
    <table>
        <tr>
            <td><form:label path = "name">Hostname:</form:label></td>
            <td><form:input path = "name" /></td>
        </tr>
        <tr>
            <td><form:label path = "ip">IPv4 Address:</form:label></td>
            <td><form:input path = "ip" value="${ip}"/></td>
        </tr>
        <tr>
            <td><form:label path = "port">Port:</form:label></td>
            <td><form:input path = "port" value="22"/><small> <b>Hint:</b> If configured otherwise please <u><i>correct the value</i></u>.</small></td>
        </tr>
        <tr>
            <td><form:label path = "usernameCred">Admin User (Optional):</form:label></td>
            <td><form:input path = "usernameCred" /><small> <b>Hint:</b> If only the default <u><i>admin username</i></u> has to be updated.</small></td>
        </tr>
        <tr>
            <td><form:label path = "passwordCred">Admin Password (Optional):</form:label></td>
            <td><form:input path = "passwordCred" type = "password" /><small> <b>Hint:</b> If only the default <u><i>admin password</i></u> has to be updated.</small></td>
        </tr>
            <%--        <tr>--%>   <%-- THIS SHOULD BE ONLY FOR USERS FUNCTIONALITY --%>
            <%--            <td><form:label path = "password" >Password</form:label></td>--%>
            <%--            <td><form:input path = "password" type="password"/></td>--%>
            <%--        </tr>--%>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Update"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>
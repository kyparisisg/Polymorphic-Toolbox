<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Manage User - Search Operation</title>
</head>

<body>
<h2>Search User</h2>
<form:form method = "POST" action = "/api/users/get">
    <table>
        <tr>
            <td><form:label path = "email">Email</form:label></td>
            <td><form:input path = "email" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Search"/>
            </td>
        </tr>
    </table>
</form:form>

<%--<form:form method = "DELETE" action = "/api/users/delete">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td><form:label path = "email2">Email</form:label></td>--%>
<%--            <td><form:input path = "email2" /></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td colspan = "2">--%>
<%--                <input type = "submit" value = "Submit"/>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form:form>--%>

</body>

</html>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Manage Server - Search Operation</title>
</head>

<body>
<h2>Search Server</h2>
    <form:form method = "POST" action = "/api/servers/get">
        <table>
            <tr>
                <td><form:label path = "ip">IPv4 Address</form:label></td>
                <td><form:input path = "ip" /></td>
            </tr>
            <tr>
                <td colspan = "2">
                    <input type = "submit" value = "Search"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>

</html>
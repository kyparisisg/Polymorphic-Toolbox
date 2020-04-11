Form to uploads file from server.

**For now just to test the aws api, upload a file from your localhost**

**uncomment the following form to submit the file to our APIs and from there make the aws api call**
**Look AwsController Post Request mapping method**


EXAMPLE FORM FOR POST ON OUR APIs

<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
<title>Upload File - Transfer Operation</title>
</head>

<body>
<h2>Upload File</h2>
    <form:form method = "POST" action = "/aws/upload">
        <table>
        <tr>
        <td><form:label path = "fileName">File</form:label></td>
        <td><form:input path = "fileName" /></td>
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

Form to uploads file from server.

**For now just to test the aws api, upload a file from your localhost**

**uncomment the following form to submit the file to our APIs and from there make the aws api call**
**Look AwsController Post Request mapping method**


EXAMPLE FORM FOR POST ON OUR APIs

<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<html>
<head>
    <title>Upload File - Transfer Operation</title>
</head>

<body>
<h2>Upload File</h2>
<form:form method = "POST" action = "/client/aws/download">
    <table>
        <tr>
            <td><form:label path = "file_name">File Name</form:label></td>
            <td><form:input path = "file_name" /></td>
        </tr>
        <tr>
            <td><form:label path = "s3dir">S3 Directory Path</form:label></td>
            <td><form:input path = "s3dir" /></td>
        </tr>
        <tr>
            <td><form:label path = "bucket">Bucket Name</form:label></td>
            <td><form:input path = "bucket" /></td>
        </tr>

        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Update"/>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

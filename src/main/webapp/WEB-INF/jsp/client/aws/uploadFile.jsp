Form to uploads file from server.

**For now just to test the aws api, upload a file from your localhost**

**uncomment the following form to submit the file to our APIs and from there make the aws api call**
**Look AwsController Post Request mapping method**


EXAMPLE FORM FOR POST ON OUR APIs

<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<html>
<head>
<title>Upload File - Transfer Operation</title>
</head>

<body>
<h2>Upload File</h2>
    <form:form method = "POST" action = "/client/aws/upload">
        <table>
            <tr>
                <td><form:label path = "s3dir">S3 Directory Path</form:label></td>
                <td><form:input path = "s3dir" /></td>
            </tr>
            <tr>
                <td><form:label path = "ipv4">ipv4 Address</form:label></td>
                <td><form:input path = "ipv4" /></td>
            </tr>
            <tr>
                <td><form:label path = "user">User</form:label></td>
                <td><form:input path = "user" /></td>
            </tr>
            <tr>
                <td><form:label path = "bucket">Bucket Name</form:label></td>
                <td><form:input path = "bucket" /></td>
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

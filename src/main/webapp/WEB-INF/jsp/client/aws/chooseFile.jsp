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

<form action="/client/aws/fileinput" method="POST" enctype="multipart/form-data">
    <input type="file" name="file" size="100"/>
    <br/>
    <input type="submit" value="Upload File"/>
</form>

</body>

</html>

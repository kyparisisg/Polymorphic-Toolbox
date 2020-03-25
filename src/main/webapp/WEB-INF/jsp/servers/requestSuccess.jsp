<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="server" class="com.temple.polymorphic.toolbox.dto.ServerDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Request status</title>		     <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>

<body>
<h1>Submitted Server Information</h1>
<p>Request Status Success :: ${request}</p>
<div class="container my-2">

    <div th:case="*">
        <table class="table table-striped table-responsive-md">
            <thead>
            <tr>
                <th>ID</th>
                <th>Hostname</th>
                <th>IPv4</th>
                <th>Port</th>
                <th>Admin User</th>
            </tr>
            </thead>
                <tr>
                    <td>${id}</td>
                    <td>${name}</td>
                    <td>${ip}</td>
                    <td>${port}</td>
                    <td>${usernameCred}</td>
                </tr>
            </tr>
        </table>
    </div>
    <tr>
        <td>Return to :</td>
        <td><a href="/api/servers/">Manage Servers</a></td>
    </tr>

</div>
</body>
</html>
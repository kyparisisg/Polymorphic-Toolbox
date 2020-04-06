<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="server" class="com.temple.polymorphic.toolbox.dto.ServerDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>All Servers</title>		     <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>All Servers</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css">
</head>

<body>
<h2>Set User's Permissions</h2>
<h3>(email must be the same as one in the db to update the rest information)</h3>
<form:form method = "POST" action = "/api/users/permissions">

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
                    <th>Health</th>
                </tr>
                </thead>
                <c:forEach items="${list}" var="server">
                    <tr>
                        <td>${server.id}</td>
                        <td>${server.name}</td>
                        <td>${server.ip}</td>
                        <td>${server.port}</td>
                        <td>${server.usernameCred}</td>
                        <td>${server.health}</td>
                    </tr>
                </c:forEach>
                </tr>
            </table>
        </div>
            <tr>
                <td><form:label path = "email">Email</form:label></td>
                <td><form:input path = "email" type="hidden" value="${email}"/></td>
            </tr>

<%--            <tr>--%>
<%--                <td><form:label path = "ip">IPv4</form:label></td>--%>
<%--                <td><form:input path = "ip"/></td>--%>
<%--            </tr>--%>
            <tr>
                <td><form:label path = "id">Server ID</form:label></td>
                <td><form:input path = "id"/></td>
            </tr>

            <tr>
                <td colspan = "2">
                    <input type = "submit" value = "Set"/>
                </td>
            </tr>
    </div>
</form:form>

<%--    <div>--%>
<%--        <form:form method = "POST" action = "/api/users/permissions">--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "email">Email</form:label></td>--%>
<%--                    <td><form:input path = "email" type="hidden" value="${email}"/></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "ip">IPv4</form:label></td>--%>
<%--                    <td><form:input path = "ip"/></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td colspan = "2">--%>
<%--                        <input type = "submit" value = "Set"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>

<%--        </form:form>--%>
<%--    </div>--%>

</body>

</html>

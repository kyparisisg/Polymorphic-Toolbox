<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="server" class="com.temple.polymorphic.toolbox.dto.ServerDto"/>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>

<body>
<h2>User Update Information</h2>
<h3>(email must be the same as one in the db to update the rest information)</h3>
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
            <td>Return to :</td>
            <td><a href="/api/servers/">Manage Servers</a></td>
        </tr>

    </div>
    <div>
        <form:form name="setPerms" method = "POST" action = "/api/users/permissions">
            <table>
                <tr>
                    <td><form:label path = "email">Email</form:label></td>
                    <td><form:input path = "email" value="${email}"/></td>
                </tr>
                <tr>
                    <td><form:label path = "ip">IPv4</form:label></td>
                    <td><form:input path = "ip"/></td>
                </tr>
                <tr>
                    <td colspan = "2">
                        <input type = "submit" value = "Set"/>
                    </td>
                </tr>
            </table>

        </form:form>
    </div>

<!--  -->
<%--    <div>--%>
<%--        <form:form name="deletePerms" method = "DELETE" action = "/api/users/permissions">--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "email">Email</form:label></td>--%>
<%--                    <td><form:input path = "email" value="${email}"/></td>--%>
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
<%--    --%>
<%--        </form:form>--%>
<%--    </div>--%>
</body>

</html>

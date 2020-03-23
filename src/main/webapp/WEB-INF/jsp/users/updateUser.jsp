<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>

<body>
<h2>User Update Information</h2>
<h3>(email must be the same as one in the db to update the rest information)</h3>
<form:form method = "POST" action = "/api/users/update">
    <table>
        <tr>
            <td><form:label path = "firstName">First Name</form:label></td>
            <td><form:input path = "firstName" /></td>
        </tr>
        <tr>
            <td><form:label path = "lastName">Last Name</form:label></td>
            <td><form:input path = "lastName" /></td>
        </tr>
        <tr>
            <td><form:label path = "email">Email</form:label></td>
<%--            <%String givenEmail=request.getParameter("email"); %>--%>
<%--            <jsp:useBean id="userDto" scope="request" type="com.temple.polymorphic.toolbox.dto.UserDto"/>--%>
<%--            <c:set var = "givenEmail" scope = "session" value = "${email}"/>--%>
            <td><form:input path = "email" value="${email}"/></td>
        </tr>
        <tr>
            <td><form:label path = "role">Role</form:label></td>
            <td><form:input path = "role" /></td>
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



<%--<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>--%>
<%--<html xmlns:th="http://www.thymeleaf.org">--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Spring MVC Form Handling</title>--%>
<%--</head>--%>

<%--<body>--%>
<%--<h2>User Update Information</h2>--%>
<%--<h3>(email must be the same as one in the db to update the rest information)</h3>--%>
<%--<c:choose>--%>
<%--    <c:when test="${not empty email}">--%>

<%--        <form:form name="exists" method = "POST" action = "/api/users/update">--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "firstName">First Name</form:label></td>--%>
<%--                    <td><form:input path = "firstName" /></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "lastName">Last Name</form:label></td>--%>
<%--                    <td><form:input path = "lastName" /></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "email">Email</form:label></td>--%>
<%--                    <td><form:input path = "email" value="${email}" /></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "role">Role</form:label></td>--%>
<%--                    <td><form:input path = "role" /></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td colspan = "2">--%>
<%--                        <input type = "submit" value = "Submit"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--        </form:form>--%>

<%--    </c:when>--%>
<%--    <c:otherwise>--%>

<%--        <form:form name="new" method = "POST" action = "/api/users/update">--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "firstName">First Name</form:label></td>--%>
<%--                    <td><form:input path = "firstName" /></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "lastName">Last Name</form:label></td>--%>
<%--                    <td><form:input path = "lastName" /></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "email">Email</form:label></td>--%>
<%--                    <td><form:input path = "email" /></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td><form:label path = "role">Role</form:label></td>--%>
<%--                    <td><form:input path = "role" /></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td colspan = "2">--%>
<%--                        <input type = "submit" value = "Submit"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--        </form:form>--%>

<%--    </c:otherwise>--%>
<%--</c:choose>--%>
<%--</body>--%>

<%--</html>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.temple.polymorphic.toolbox.dto.UserDto"/>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<!------------------------------------------------------------------------------
NOTE TO FRONT END TEAM - THIS IS A DEBUG FILE ONLY - NO NEED FOR JS OR CSS
------------------------------------------------------------------------------->
<body>
<p>${userDto.email}</p>
<br>
<p>${userDto.role}</p>
</body>
</html>
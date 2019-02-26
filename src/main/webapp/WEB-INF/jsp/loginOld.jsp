<%--
  Created by IntelliJ IDEA.
  User: zheka
  Date: 19.02.2019
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form name="loginForm" method="POST" action="login">
        <input type="hidden" name="command" value="login"/>
        Login: <br/>
        <input type="text" name="login" value="" />
        <br/> Password <br/>
        <input type="password" name="password" value="" />
            <br/>
                <c:out value="${errorMessage}"/>
            <br/>
        <input type="submit" value="Log in" />
    </form>
</body>
</html>

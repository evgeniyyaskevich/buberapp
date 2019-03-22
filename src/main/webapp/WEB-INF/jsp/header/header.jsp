<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Buber</title>
    <link rel="stylesheet" href="<c:url value="/css/header.css" />">
</head>

<body>

<form action="main" method="POST">
    <input type="hidden" name="command" value="log_out">
    <input type="submit" class="log-out-button"
           value="<fmt:message key="header.logOut" bundle="${content}"/>">
</form>

</body>

</html>
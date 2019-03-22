<%@ page errorPage="error404.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${language}" scope="session"/>
    <fmt:setBundle basename="applicationContent" var="content" scope="application"/>

    <link rel="stylesheet" href="<c:url value="/css/error.css" />">
    <title>404</title>
</head>

<body>
<div class="error-container">
    <h1>404</h1>
    <h2 class="not-found"> <fmt:message key="error.404" bundle="${content}"/> </h2>
</div>
</body>
</html>

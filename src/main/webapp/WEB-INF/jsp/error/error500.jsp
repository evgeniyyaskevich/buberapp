<%@ page errorPage="error500.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${language}" scope="session"/>
    <fmt:setBundle basename="applicationContent" var="content" scope="application"/>

    <meta charset="utf-8">
    <link rel="stylesheet" href="<c:url value="/css/error.css" />">
    <title>500</title>
</head>

<body>
<div class="error-container">
    <h1><fmt:message key="error.oops" bundle="${content}"/></h1>
    <h2 class="not-found">
        <fmt:message key="error.500.work" bundle="${content}"/> <br>
        <fmt:message key="error.500.apologise" bundle="${content}"/>
    </h2>
</div>
</body>
</html>

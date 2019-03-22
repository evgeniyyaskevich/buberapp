<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>

<html>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="applicationContent" var="content" scope="application"/>

<head>
    <title><fmt:message key="title.successOrder" bundle="${content}"/></title>
    <link rel="stylesheet" href="<c:url value="/css/successOrder.css" />">
    <title>Success</title>
</head>
<div id="openModal" class="modalDialog">
    <div>
        <div class="applicationContent">
            <h1 class="text-form-header"><fmt:message key="order.success" bundle="${content}"/></h1>
            <div class="text-form">
                <fmt:message key="order.thanks" bundle="${content}"/> <br> <br>
            </div>
            <form method="POST">
                <input type="hidden" name="command" value="return_main">
                <input class="orderButton" type="submit"
                       value="<fmt:message key="button.returnToMain" bundle="${content}"/>"/>
            </form>
        </div>
    </div>
</div>
</html>

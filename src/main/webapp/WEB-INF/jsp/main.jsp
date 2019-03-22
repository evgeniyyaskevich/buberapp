<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <jsp:include page="header/languageHeader.jsp"/>
    <title><fmt:message key="title.buberMain" bundle="${content}"/></title>
    <link rel="stylesheet" href="<c:url value="/css/main.css" />">
</head>

<body>
<jsp:include page="header/header.jsp"/>

<div class="welcome">
    <fmt:message key="main.welcomeMessage" bundle="${content}"/>, ${user.name}!
</div>
<br> <br> <br>

<c:if test="${user.level eq 'CLIENT'}">
    <div class="button-content">
        <a href="#openOrderForm">
            <input type="button" class="orderButton" id="orderButton"
                   value="<fmt:message key="main.orderButton" bundle="${content}"/>"/>
        </a>
    </div>
    <br> <br>

    <div class="applicationContent">
        <c:choose>
            <c:when test="${fn:length(applications) ne 0}">
                <div class="text-form-header">
                    <table align="center" border="2" cellspacing="2" cellpadding="2">
                        <thead align="center">
                        <tr>
                            <td>#</td>
                            <td><fmt:message key="destination" bundle="${content}"/></td>
                            <td><fmt:message key="applicationTime" bundle="${content}"/></td>
                            <td><fmt:message key="carType" bundle="${content}"/></td>
                            <td><fmt:message key="childSeat" bundle="${content}"/></td>
                            <td><fmt:message key="price" bundle="${content}"/></td>
                            <td><fmt:message key="state" bundle="${content}"/></td>
                            <td>|--|</td>
                        </tr>
                        </thead>

                        <tbody align="center">
                        <c:forEach items="${applications}" var="application">
                            <tr>
                                <td><c:out value="${application.id}"/></td>
                                <td><c:out value="${application.destination}"/></td>
                                <td><c:out value="${application.dateTime}"/></td>
                                <td><c:out value="${application.carType}"/></td>
                                <td><c:out value="${application.childSeat}"/></td>
                                <td><c:out value="${application.price}"/></td>
                                <td><c:out value="${application.state}"/></td>
                                <td>
                                    <c:if test="${application.state eq 'WAITING'}">
                                        <form action="main" method="POST" style="display: inline; margin: 0;">
                                            <input type="hidden" name="command" value="cancel_order">
                                            <input type="hidden" name="application_id" value="${application.id}">
                                            <input type="submit"
                                                   value="<fmt:message key="main.cancel" bundle="${content}"/>">
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <fmt:message key="main.emptyOrderList" bundle="${content}"/>
            </c:otherwise>
        </c:choose>
    </div>

    <div id="openOrderForm" class="modalDialog">
        <div>
            <form action="main" method="POST">
                <input type="hidden" name="command" value="form_order">
                <a href="" title="" class="close">X</a>
                <div class="applicationContent">
                    <h1 class="text-form-header">
                        <fmt:message key="main.orderForm" bundle="${content}"/>
                    </h1>
                    <div class="text-form">
                        <fmt:message key="main.orderForm.from" bundle="${content}"/>
                    </div>
                    <fmt:message key="main.orderForm.currentLocation" bundle="${content}"/> <br>
                    <div class="text-form">
                        <fmt:message key="main.orderForm.to" bundle="${content}"/>
                    </div>
                    <label>
                        <select name="destination">
                            <c:forEach items="${destinations}" var="destination">
                                <option>${destination.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <hr>
                    <label> <input type="checkbox" name="child_seat" value="true">
                        <fmt:message key="childSeat" bundle="${content}"/> </label>
                    <hr>
                    <label>
                        <input name="carType" type="radio" value="Universal" required>
                        <fmt:message key="carType.universal" bundle="${content}"/>
                        <input name="carType" type="radio" value="Minivan">
                        <fmt:message key="carType.universal" bundle="${content}"/>
                        <input name="carType" type="radio" value="Elite">
                        <fmt:message key="carType.elite" bundle="${content}"/> <br>
                    </label> <br> <br>
                    <input class="orderButton" type="submit"
                           value="<fmt:message key="main.orderForm.next" bundle="${content}"/>"/>
                </div>
            </form>
        </div>
    </div>
</c:if>

<c:if test="${user.level eq 'DRIVER'}">
    <jsp:include page="role/driver.jsp">
        <jsp:param name="applications" value="${applications}"/>
        <jsp:param name="errorMessage" value="${errorMessage}"/>
    </jsp:include>
</c:if>

<c:if test="${user.level eq 'ADMIN'}">
    <jsp:include page="role/admin.jsp">
        <jsp:param name="users" value="${users}"/>
    </jsp:include>
</c:if>

</body>
</html>
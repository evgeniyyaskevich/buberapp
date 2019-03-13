<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<html>

<head>
    <title>Buber Main</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css" />">
</head>

<body>

<jsp:include page="header.jsp"/>

<%--<fmt:setLocale value="en"/>
<fmt:bundle basename="mainPageContent">
    <fmt:message key="welcomeMessage" var="welcomeMessage"/>
    <fmt:message key="from" var="from"/>
    <fmt:message key="to" var="to"/>
    <fmt:message key="currentLocation" var="currentLocation"/>
    <fmt:message key="destination" var="destination"/>
    <fmt:message key="applicationTime" var="applicationTime"/>
    <fmt:message key="childSeat" var="childSeat"/>
    <fmt:message key="carType" var="carType"/>
    <fmt:message key="price" var="price"/>
    <fmt:message key="state" var="state"/>
</fmt:bundle>--%>


<div class="welcome">
    Welcome to Buber, ${user.name}!
</div>
<br> <br> <br>

<c:if test="${user.level eq 'CLIENT'}">
    <c:out value=""/>
    <div class="button-content">
        <a href="#openModal">
            <input type="button" class="orderButton" id="orderButton" value="Do you want to order?"/> </a>
    </div>

    <div id="openModal" class="modalDialog">
        <div>
            <form action="main" method="POST">
                <input type="hidden" name="command" value="form_order">
                <a href="" title="Close" class="close">X</a>
                <div class="applicationContent">
                    <h1 class="text-form-header">Order Form</h1>
                    <div class="text-form">
                        From:
                    </div>
                        Current Location <br>
                    <div class="text-form">
                        To:
                    </div>
                    <label>
                        <select name="destination">
                            <c:forEach items="${destinations}" var="destination">
                                <option>${destination.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <hr>
                    <label> <input type="checkbox" name="child_seat" value="true"> Child Seat </label>
                    <hr>
                    <label>
                        <input name="carType" type="radio" value="Universal" required> Universal
                        <input name="carType" type="radio" value="Minivan"> Minivan
                        <input name="carType" type="radio" value="Elite"> Elite <br>
                    </label> <br> <br>
                    <input class="orderButton" type="submit" value="next"/>
                </div>
            </form>
        </div>
    </div>
</c:if>

<c:if test="${user.level eq 'DRIVER'}">
    <div class="applicationContent">
        <div>
            <form action="main" method="POST">
                <input type="hidden" name="command" value="accept_order"/>
                <input type="number" name="orderNumber" value="" placeholder="application id" required/>
                <input type="submit" value="Accept order"/>
            </form>
        </div>

        <div class="error-message">
            <c:out value="${errorMessage}"/>
        </div>
    </div>
</c:if>

<c:if test="${user.level eq 'ADMIN'}">
    <jsp:include page="admin.jsp">
        <jsp:param name="users" value="${users}"/>
    </jsp:include>
</c:if>

<br> <br> <br>
<div class="applicationContent">
    <c:if test="${user.level ne 'ADMIN'}">
        <c:choose>
            <c:when test="${fn:length(applications) ne 0}">
                <div class="text-form-header">
                    <table align="center" border="2" cellspacing="2" cellpadding="2">
                        <thead align="center">
                        <tr>
                            <td>#</td>
                            <td>destination</td>
                            <td>applicationTime</td>
                            <td>carType</td>
                            <td>childSeat</td>
                            <td>price</td>
                            <td>state</td>
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
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <c:out value="Your list of orders is empty."/>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>
</body>

</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<html>

<head>
    <title>Buber Main</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css" />">
</head>

<body>

<jsp:include page="header.jsp"/>

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
<c:if test="${user.level eq 'CLIENT'}">
    <%--dawdnjfnjdsg
    ${fn:length(applications)}
    ${fn:length(applications)}--%>
    <%--<c:choose>
        <c:when test="${fn:length(applications)}">
            <div class="text-form-header">
                Your list is empty. Make order.
            </div>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>--%>
</c:if>


<div class="text-form-header">
    <table align="center" border="2" cellspacing="2" cellpadding="2">
        <thead align="center">
        <tr>
            <td>#</td>
            <td>destination</td>
            <td>application time</td>
            <td>car type</td>
            <td>child seat</td>
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


</body>

</html>

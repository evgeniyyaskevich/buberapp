<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <jsp:include page="header/languageHeader.jsp"/>

    <link rel="stylesheet" href="<c:url value="/css/order.css" />">
    <title><fmt:message key="title.order" bundle="${content}"/></title>
</head>
<body>

<jsp:include page="header/header.jsp"/>

<div class="welcome">
    <h3><fmt:message key="order.buberOrder" bundle="${content}"/></h3>
</div>

<table border="1" align="center" class="table-form" cellpadding="10" cellspacing="5">
    <tr class="text-form">
        <td>
            <fmt:message key="main.orderForm.from" bundle="${content}"/>:
            <b><fmt:message key="main.orderForm.currentLocation" bundle="${content}"/></b> <br>
            <fmt:message key="main.orderForm.to" bundle="${content}"/>:
            <b>${application.destination}</b> <br>
            <fmt:message key="carType" bundle="${content}"/>:
            <b>${application.carType}</b> <br>
            <fmt:message key="childSeat" bundle="${content}"/>:
            <b>${application.childSeat}</b> <br>
            <fmt:message key="price" bundle="${content}"/>:
            <b>${application.price} $</b> <br>
        </td>
    </tr>
</table>

<div class="order-div">
    <form action="main" method="POST">
        <input type="hidden" name="command" value="make_order">
        <input type="submit" class="orderButton"
               value="<fmt:message key="order.confirmOrder" bundle="${content}"/>">
    </form>

    <form method="POST">
        <input type="hidden" name="command" value="return_main">
        <input class="returnButton" type="submit"
               value="<fmt:message key="button.returnToMain" bundle="${content}"/>"/>
    </form>

</div>
</body>
</html>
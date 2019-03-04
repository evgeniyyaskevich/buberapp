<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/order.css" />">
    <title>Order Result</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="welcome">
    <h3>Buber Order</h3>
</div>

<table border="1" align="center" class="table-form" cellpadding="10" cellspacing="5">
    <tr class="text-form">
        <td>
            From: <b> current Location</b> <br>
            To: <b>${application.destination}</b> <br>
            CarType: <b>${application.carType}</b> <br>
            ChildSeat: <b>${application.childSeat}</b> <br>
            Price: <b>${application.price} $</b> <br>
        </td>
    </tr>
</table>

<div class="order-div">
    <form action="main" method="POST">
        <input type="hidden" name="command" value="make_order">
        <input type="submit" class="orderButton" value="Confirm Order">
    </form>
</div>
</body>

</html>
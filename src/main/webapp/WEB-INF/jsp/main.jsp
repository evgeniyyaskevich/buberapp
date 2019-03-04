<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"  pageEncoding="UTF-8" %>
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

</body>

</html>

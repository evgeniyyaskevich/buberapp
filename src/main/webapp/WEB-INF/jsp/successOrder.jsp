<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>

<head>
    <link rel="stylesheet" href="<c:url value="/css/successOrder.css" />">
</head>
<div id="openModal" class="modalDialog">
    <div>
        <form action="main" method="POST">
            <input type="hidden" name="command" value="form_order">
            <div class="applicationContent">
                <h1 class="text-form-header">Success</h1>
                <div class="text-form">
                    Thanks for your order! <br> <br>
                </div>
                <form method="GET">
                    <input class="orderButton" type="submit" value="Return to Main"/>
                </form>
            </div>
        </form>
    </div>
</div>
</html>

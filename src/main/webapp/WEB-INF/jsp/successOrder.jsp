<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>

<head>
    <link rel="stylesheet" href="<c:url value="/css/successOrder.css" />">
    <title>Success</title>
</head>
<div id="openModal" class="modalDialog">
    <div>
            <div class="applicationContent">
                <h1 class="text-form-header">Success</h1>
                <div class="text-form">
                    Thanks for your order! <br> <br>
                </div>
                <form method="POST">
                    <input type="hidden" name="command" value="return_main">
                    <input class="orderButton" type="submit" value="Return to Main"/>
                </form>
            </div>
    </div>
</div>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Welcome to Buber App</title>
    <link rel="stylesheet" href="<c:url value="/css/main.css" />">
</head>

<h1>HELLO</h1>
<div class="user-info">
    <h3 align="center">User INFO</h3>
    <p>Name - ${user.name}</p>
    <p>Bonus - ${user.bonus}</p>
</div>

<div>

</div>
</html>
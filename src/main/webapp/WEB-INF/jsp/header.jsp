<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Buber App</title>
    <link rel="stylesheet" href="<c:url value="/css/header.css" />">
</head>

<body>

<form action="main" method="POST">
    <input type="hidden" name="command" value="log_out">
    <input type="submit" value="Log Out" class="log-out-button">
</form>

</body>

</html>
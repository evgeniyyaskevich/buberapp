<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <fmt:setLocale value="${language}" scope="session"/>
    <fmt:setBundle basename="applicationContent" var="content" scope="application"/>
</head>

<body>
<div class="language-form">
    <form method="POST">
        <input type="hidden" name="command" value="set_language">
        <label>
            <select id="language" name="language" onchange="submit()">
                <option value="ru" ${sessionScope.language == 'ru' ? 'selected' : ''}>Русский</option>
                <option value="en" ${sessionScope.language == 'en' ? 'selected' : ''}>English</option>
            </select>
        </label>
    </form>
</div>
</body>
</html>
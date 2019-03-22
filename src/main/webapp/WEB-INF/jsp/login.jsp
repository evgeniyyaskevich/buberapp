<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="header/languageHeader.jsp"/>

    <title><fmt:message key="title.loginForm" bundle="${content}"/></title>
    <link rel="stylesheet" href="<c:url value="/css/login.css" />">
</head>

<body>

<div class="login">
    <div class="login-screen">
        <div class="app-title">
            <h1><fmt:message key="login.signIn" bundle="${content}"/></h1>
        </div>

        <div class="login-form">
            <form class="login-form" action="login" method="POST">
                <input type="hidden" name="command" value="log_in" required/>
                <div class="control-group">
                    <input name="login" class="login-field" value=""
                           placeholder="<fmt:message key="login.username" bundle="${content}"/>"
                           id="login-name" required>
                    <label class="login-field-icon fui-user" for="login-name"></label>
                </div>

                <div class="control-group">
                    <input type="password" name="password" class="login-field" value=""
                           placeholder="<fmt:message key="login.password" bundle="${content}"/>"
                           id="login-pass" required>
                    <label class="login-field-icon fui-lock" for="login-pass"></label>
                </div>

                <div class="error-message">
                    <c:out value="${errorMessage}"/>
                </div>

                <div class="btn-submit">
                    <input class="btn btn-submit" type="submit" id="submitR"
                           value="<fmt:message key="login.button.submit" bundle="${content}"/>"/>
                </div>
            </form>

            <a class="register-link" href="<c:url value="/register"/> ">
                <fmt:message key="login.createAccount" bundle="${content}"/>
            </a>
        </div>
    </div>
</div>
</body>
</html>
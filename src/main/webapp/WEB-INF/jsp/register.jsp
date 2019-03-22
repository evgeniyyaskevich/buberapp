<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="header/languageHeader.jsp"/>

    <title><fmt:message key="title.registerForm" bundle="${content}"/></title>
    <link rel="stylesheet" href="<c:url value="/css/register.css" />">
</head>

<body>

<div class="register">
    <div class="register-screen">
        <div class="app-title">
            <h1><fmt:message key="register.signUp" bundle="${content}"/></h1>
        </div>

        <div class="register-form">
            <form class="register-form" action="register" method="POST">
                <input type="hidden" name="command" value="register" required/>
                <div class="control-group">
                    <input type="text" name="login" class="login-field" value=""
                           placeholder="<fmt:message key="register.username" bundle="${content}"/>"
                           id="login-name" required>
                    <label class="login-field-icon fui-user" for="login-name"></label>
                </div>

                <div class="control-group">
                    <input type="password" name="password"
                           class="login-field" value=""
                           placeholder="<fmt:message key="register.password" bundle="${content}"/>"
                           id="login-pass" required>
                    <label class="login-field-icon fui-lock" for="login-pass"></label>
                </div>

                <div class="control-group">
                    <input type="password" name="confirmPassword"
                           class="login-field" value=""
                           placeholder="<fmt:message key="register.confirmPassword" bundle="${content}"/>"
                           id="login-pass-1" required>
                    <label class="login-field-icon fui-lock" for="login-pass"></label>
                </div>


                <div class="error-message">
                    <c:out value="${errorMessage}"/>
                </div>
                <input class="btn btn-primary btn-large btn-block" type="submit"
                       value="<fmt:message key="register.signUp" bundle="${content}"/>"/>
            </form>

            <a class="login-link" href="<c:url value="/login"/> ">
                <fmt:message key="register.signIn" bundle="${content}"/>
            </a>
        </div>
    </div>
</div>
</body>
</html>

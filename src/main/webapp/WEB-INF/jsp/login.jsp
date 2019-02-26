<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
    <link rel="stylesheet" href="<c:url value="/css/login.css" />">
</head>

<body>
<div class="login">
    <div class="login-screen">
        <div class="app-title">
            <h1>Sign in</h1>
        </div>

        <div class="login-form">
            <form class="login-form" action="login" method="POST">
                <input type="hidden" name="command" value="login" required/>
                <div class="control-group">
                    <input type="text" name="login" class="login-field" value="" placeholder="username"
                           id="login-name" required>
                    <label class="login-field-icon fui-user" for="login-name"></label>
                </div>

                <div class="control-group">
                    <input type="password" name="password"
                           class="login-field" value="" placeholder="password" id="login-pass" required>
                    <label class="login-field-icon fui-lock" for="login-pass"></label>
                </div>

                <div class="error-message">
                    <c:out value="${errorMessage}"/>
                </div>
                <input class="btn btn-primary btn-large btn-block" type="submit" value="Sign in"/>
            </form>

            <a class="register-link" href="<c:url value="/register"/> ">Create account?</a>
        </div>
    </div>
</div>
</body>

</html>
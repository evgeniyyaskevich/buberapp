<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
    <link rel="stylesheet" href="<c:url value="/css/register.css" />">
</head>

<body>
<div class="register">
    <div class="register-screen">
        <div class="app-title">
            <h1>Sign up</h1>
        </div>

        <div class="register-form">
            <form class="register-form" action="register" method="POST">
                <input type="hidden" name="command" value="register" required/>
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

                <div class="control-group">
                    <input type="password" name="confirmPassword"
                           class="login-field" value="" placeholder="confirm password" id="login-pass-1" required>
                    <label class="login-field-icon fui-lock" for="login-pass"></label>
                </div>


                <div class="error-message">
                    <c:out value="${errorMessage}"/>
                </div>
                <input class="btn btn-primary btn-large btn-block" type="submit" value="Sign up"/>
            </form>

            <a class="login-link" href="<c:url value="/login"/> ">Sign in?</a>
        </div>
    </div>
</div>
</body>

</html>

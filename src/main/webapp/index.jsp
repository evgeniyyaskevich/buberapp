<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<c:redirect url="/main"/>

<fmt:bundle basename="config">
    <fmt:message key="path.page.login" var="var"/>
    <jsp:forward page="${var}"/>
</fmt:bundle>

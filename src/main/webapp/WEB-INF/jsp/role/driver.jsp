<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
    <link rel="stylesheet" href="<c:url value="/css/main.css" />">
</head>

<body>
<div class="applicationContent">
    <div>
        <form action="main" method="POST">
            <input type="hidden" name="command" value="accept_order"/>
            <input type="number" name="orderNumber" value=""
                   placeholder="<fmt:message key="driver.applicationId" bundle="${content}"/>"
                   required/>
            <input type="submit" value="<fmt:message key="driver.acceptOrder" bundle="${content}"/>"/>
        </form>
    </div>

    <div class="error-message">
        <c:out value="${errorMessage}"/>
    </div>

    <c:choose>
        <c:when test="${fn:length(applications) ne 0}">
            <div class="text-form-header">
                <table align="center" border="2" cellspacing="2" cellpadding="2">
                    <thead align="center">
                    <tr>
                        <td>#</td>
                        <td><fmt:message key="destination" bundle="${content}"/></td>
                        <td><fmt:message key="applicationTime" bundle="${content}"/></td>
                        <td><fmt:message key="carType" bundle="${content}"/></td>
                        <td><fmt:message key="childSeat" bundle="${content}"/></td>
                        <td><fmt:message key="price" bundle="${content}"/></td>
                        <td><fmt:message key="state" bundle="${content}"/></td>
                    </tr>
                    </thead>

                    <tbody align="center">
                    <c:forEach items="${applications}" var="application">
                        <tr>
                            <td><c:out value="${application.id}"/></td>
                            <td><c:out value="${application.destination}"/></td>
                            <td><c:out value="${application.dateTime}"/></td>
                            <td><c:out value="${application.carType}"/></td>
                            <td><c:out value="${application.childSeat}"/></td>
                            <td><c:out value="${application.price}"/></td>
                            <td><c:out value="${application.state}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <fmt:message key="main.emptyOrderList" bundle="${content}"/>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/admin.css" />">
</head>

<body>

<div id="openModalForAdminPanel" class="modalDialogForAdminPanel">
    <div align="center">
        <div class="tabs">
            <input id="tab1" type="radio" name="tabs" checked>
            <label for="tab1" title="Tab 1">
                <fmt:message key="admin.users" bundle="${content}"/>
            </label>

            <input id="tab2" type="radio" name="tabs">
            <label for="tab2" title="Tab 2">
                <fmt:message key="admin.cars" bundle="${content}"/>
            </label>

            <input id="tab3" type="radio" name="tabs">
            <label for="tab3" title="Tab 3">
                <fmt:message key="admin.destinations" bundle="${content}"/>
            </label>

            <section id="content-tab1" class="panel-content">
                <table border="1" cellpadding="3" cellspacing="0" align="center">
                    <thead>
                    <tr>
                        <th><fmt:message key="id" bundle="${content}"/></th>
                        <th><fmt:message key="name" bundle="${content}"/></th>
                        <th><fmt:message key="levelAccess" bundle="${content}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <th><c:out value="${user.id}"/></th>
                            <th><c:out value="${user.name}"/></th>
                            <th><c:out value="${user.level}"/></th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <h3 align="center"><fmt:message key="admin.blackList" bundle="${content}"/></h3>
                <table border="1" cellpadding="3" cellspacing="0" align="center">
                    <thead>
                    <tr>
                        <th><fmt:message key="id" bundle="${content}"/></th>
                        <th><fmt:message key="admin.reason" bundle="${content}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${blackListRecords}" var="record">
                        <tr>
                            <th>${record.userId}</th>
                            <th>${record.reason}</th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <h3 align="center"><fmt:message key="admin.updateUser" bundle="${content}"/></h3>
                <form action="main" method="post">
                    <input type="hidden" name="command" value="update_user">
                    <fmt:message key="admin.who" bundle="${content}"/>:
                    <label>
                        <select name="user_id">
                            <c:forEach items="${users}" var="user">
                                <option value="${user.id}"><c:out value="${user.name}"/></option>
                            </c:forEach>
                        </select>
                    </label>
                    <fmt:message key="name" bundle="${content}"/>:
                    <label> <input type="text" value="" name="user_name"> </label>
                    <fmt:message key="admin.access" bundle="${content}"/>:
                    <label>
                        <select name="level_access">
                            <option value="ADMIN"><fmt:message key="role.admin" bundle="${content}"/></option>
                            <option value="DRIVER"><fmt:message key="role.driver" bundle="${content}"/></option>
                            <option value="CLIENT"><fmt:message key="role.client" bundle="${content}"/></option>
                        </select>
                    </label>
                    <input type="submit" class="great_btn"
                           value="<fmt:message key="button.update" bundle="${content}"/>">
                </form>

                <br>
                <h3 align="center"><fmt:message key="admin.deleteUser" bundle="${content}"/></h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="delete_user">
                    <fmt:message key="admin.who" bundle="${content}"/>:
                    <label>
                        <select name="user_id">
                            <c:forEach items="${users}" var="user">
                                <option value="${user.id}"><c:out value="${user.name}"/></option>
                            </c:forEach>
                        </select>
                    </label>
                    <input type="submit" align="center" class="great_btn"
                           value="<fmt:message key="button.delete" bundle="${content}"/>">
                </form>

                <br>
                <h3 align="center"><fmt:message key="admin.addToBlackList" bundle="${content}"/></h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="add_user_to_black_list">
                    <fmt:message key="admin.who" bundle="${content}"/>:
                    <label>
                        <select name="user_id">
                            <c:forEach items="${users}" var="user">
                                <option value="${user.id}">
                                    <c:out value="${user.name}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                    <fmt:message key="admin.reason" bundle="${content}"/>:
                    <label> <input type="text" name="reason"> </label>
                    <input type="submit" align="center" class="great_btn"
                           value="<fmt:message key="button.add" bundle="${content}"/>">
                </form>

                <br>
                <h3 align="center"><fmt:message key="admin.deleteFromBlackList" bundle="${content}"/></h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="delete_user_from_black_list">
                    <fmt:message key="admin.who" bundle="${content}"/>:
                    <label>
                        <select name="user_id">
                            <c:forEach items="${users}" var="user">
                                <option value="${user.id}">
                                    <c:out value="${user.name}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                    <input type="submit" align="center" class="great_btn"
                           value="<fmt:message key="button.delete" bundle="${content}"/>">
                </form>
            </section>

            <section id="content-tab2">
                <table border="1" cellpadding="3" cellspacing="0" align="center">
                    <thead>
                    <tr>
                        <th><fmt:message key="id" bundle="${content}"/></th>
                        <th><fmt:message key="driverId" bundle="${content}"/></th>
                        <th><fmt:message key="carType" bundle="${content}"/></th>
                        <th><fmt:message key="childSeat" bundle="${content}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cars}" var="car">
                        <tr>
                            <th>${car.id}</th>
                            <th>${car.driverId}</th>
                            <th>${car.type}</th>
                            <th>${car.childSeat}</th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <h3 align="center"><fmt:message key="admin.addCar" bundle="${content}"/></h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="add_car">
                    <fmt:message key="role.driver" bundle="${content}"/>:
                    <label>
                        <select name="driver_id">
                            <c:forEach items="${users}" var="user">
                                <c:if test="${user.level eq 'DRIVER'}">
                                    <option value="${user.id}">
                                        <c:out value="${user.name}(id=${user.id})"/>
                                    </option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </label>
                    <fmt:message key="admin.carBrand" bundle="${content}"/>:
                    <label> <input type="text" name="car_brand" value=""> </label>
                    <label>
                        <select name="car_type">
                            <option value="MINIVAN">
                                <fmt:message key="carType.minivan" bundle="${content}"/>
                            </option>
                            <option value="ELITE">
                                <fmt:message key="carType.elite" bundle="${content}"/>
                            </option>
                            <option value="UNIVERSAL">
                                <fmt:message key="carType.universal" bundle="${content}"/>
                            </option>
                        </select>
                    </label>
                    <label>
                        <input type="checkbox" name="child_seat" value="true">
                        <fmt:message key="childSeat" bundle="${content}"/>
                    </label> <br> <br>
                    <input type="submit" class="great_btn"
                           value="<fmt:message key="button.add" bundle="${content}"/>">
                </form>


                <h3 align="center"><fmt:message key="admin.deleteCar" bundle="${content}"/></h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="delete_car">
                    <fmt:message key="admin.carId" bundle="${content}"/>:
                    <label>
                        <select name="car_id">
                            <c:forEach items="${cars}" var="car">
                                <option value="${car.id}">${car.id}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <input type="submit" class="great_btn"
                           value="<fmt:message key="button.delete" bundle="${content}"/>">
                </form>
            </section>

            <section id="content-tab3" class="panel-content">
                <table border="1" cellpadding="3" cellspacing="0" align="center">
                    <thead>
                    <tr>
                        <th><fmt:message key="id" bundle="${content}"/></th>
                        <th><fmt:message key="name" bundle="${content}"/></th>
                        <th><fmt:message key="admin.south" bundle="${content}"/></th>
                        <th><fmt:message key="admin.north" bundle="${content}"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${destinations}" var="destination">
                        <tr>
                            <th>${destination.id}</th>
                            <th>${destination.name}</th>
                            <th>${destination.southCoord}</th>
                            <th>${destination.northCoord}</th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <h3 align="center"><fmt:message key="admin.updateDestination" bundle="${content}"/></h3>
                <form action="main" method="post">
                    <input type="hidden" name="command" value="update_destination">
                    <fmt:message key="admin.which" bundle="${content}"/>:
                    <label>
                        <select name="destination_id">
                            <c:forEach items="${destinations}" var="destination">
                                <option value="${destination.id}">${destination.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <fmt:message key="name" bundle="${content}"/>:
                    <label> <input type="text" value="" name="destination_name"> </label> <br> <br>
                    <fmt:message key="admin.south" bundle="${content}"/>:
                    <label> <input type="number" name="south_coord" required> </label>
                    <fmt:message key="admin.north" bundle="${content}"/>:
                    <label> <input type="number" name="north_coord" required> </label> <br> <br>
                    <input type="submit" class="great_btn"
                           value="<fmt:message key="button.update" bundle="${content}"/>">
                </form>

                <br>
                <h3 align="center"><fmt:message key="admin.deleteDestination" bundle="${content}"/></h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="delete_destination">
                    <fmt:message key="admin.which" bundle="${content}"/>:
                    <label>
                        <select name="destination_id">
                            <c:forEach items="${destinations}" var="destination">
                                <option value="${destination.id}">${destination.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <input type="submit" align="center" class="great_btn"
                           value="<fmt:message key="button.delete" bundle="${content}"/>">
                </form>

                <br>
                <h3 align="center"><fmt:message key="admin.addDestination" bundle="${content}"/></h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="add_destination">
                    <fmt:message key="name" bundle="${content}"/>:
                    <label> <input type="text" value="" name="destination_name" required> </label>
                    <fmt:message key="admin.south" bundle="${content}"/>:
                    <label> <input type="number" name="south_coord" required> </label>
                    <fmt:message key="admin.north" bundle="${content}"/>:
                    <label> <input type="number" name="north_coord" required> </label> <br> <br>
                    <input type="submit" align="center" class="great_btn"
                           value="<fmt:message key="button.add" bundle="${content}"/>">
                </form>
            </section>

        </div>
    </div>
</div>
</body>
</html>
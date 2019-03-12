<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/admin.css" />">
    <title>Admin Panel</title>
</head>

<body>

<div id="openModalForAdminPanel" class="modalDialogForAdminPanel">
    <div align="center">
        <div class="tabs">
            <input id="tab1" type="radio" name="tabs" checked>
            <label for="tab1" title="Tab 1">Users</label>

            <input id="tab2" type="radio" name="tabs">
            <label for="tab2" title="Tab 2">Cars</label>

            <section id="content-tab1" class="panel-content">
                <table border="1" cellpadding="3" cellspacing="0" align="center">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>name</th>
                        <th>level access</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <th>${user.id}</th>
                            <th>${user.name}</th>
                            <th>${user.level}</th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <h3 align="center">Update user</h3>
                <form action="main" method="post">
                    <input type="hidden" name="command" value="update_user">
                    Who :
                    <label> <select name="user_id">
                        <c:forEach items="${users}" var="user">
                            <option value="${user.id}">${user.name}</option>
                        </c:forEach>
                    </select> </label>
                    Name :
                    <label> <input type="text" value="Name" name="user_name"> </label>
                    Access :
                    <label> <select name="level_access">
                        <option value="ADMIN">Admin</option>
                        <option value="DRIVER">Driver</option>
                        <option value="CLIENT">Client</option>
                    </select> </label>
                    <input type="submit" value="Update" class="great_btn">
                </form>

                <br>
                <h3 align="center">Delete user</h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="delete_user">
                    Who :
                    <label> <select name="user_id">
                        <c:forEach items="${users}" var="user">
                            <option value="${user.id}">${user.name}</option>
                        </c:forEach>
                    </select> </label>
                    <input type="submit" value="Delete" align="center" class="great_btn">
                </form>
            </section>

            <section id="content-tab2">
                <table border="1" cellpadding="3" cellspacing="0" align="center">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>driver id</th>
                        <th>car type</th>
                        <th>child seat</th>
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

                <h3 align="center">Add Car</h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="add_car">
                    Driver :
                    <label>
                        <select name="driver_id">
                            <c:forEach items="${users}" var="user">
                                <c:if test="${user.level eq 'DRIVER'}">
                                    <option value="${user.id}">${user.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </label>
                    Car brand :
                    <label> <input type="text" name="car_brand" value=""> </label>
                    <label> <select name="car_type">
                        <option value="MINIVAN">MINIVAN</option>
                        <option value="ELITE">ELITE</option>
                        <option value="UNIVERSAL">UNIVERSAL</option>
                    </select> </label>
                    <label> <input type="checkbox" name="child_seat" value="true">Child Seat</label>
                    <input type="submit" value="Add" class="great_btn">
                </form>


                <h3 align="center">Delete car</h3>
                <form action="main" method="POST">
                    <input type="hidden" name="command" value="delete_car">
                    Car id :
                    <label><select name="car_id">
                        <c:forEach items="${cars}" var="car">
                            <option value="${car.id}">${car.id}</option>
                        </c:forEach>
                    </select></label>
                    <input type="submit" value="Delete" class="great_btn">
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>
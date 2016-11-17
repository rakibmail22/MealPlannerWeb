<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 10/30/16
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home</title>
    <c:set var="basePath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${basePath}/statics/style/common.css">
</head>
<body>

<div>
    <div class="leftFloat">
        <form:form commandName="selectedDishList">
            <table border="1">
                <thead>
                <tr>
                    <th>All Dishes</th>
                </tr>
                </thead>
                <c:forEach var="dish" items="${allDishes}" varStatus="loop">
                    <tr>
                        <td>
                           <%-- <label><form: type="checkbox" name="selectedDishes" value="${dish.id}" path="selectedDishList.options[${loop.index}]"/>${dish.name}
                            </label>--%>
                            <form:checkbox path="dishIdList" value="${dish.id}" label="${dish.name}"/>
                        </td>
                    </tr>
                </c:forEach>

            </table>
            <select name="daySelect">
                <option value="SUN">SUNDAY</option>
                <option value="MON">MONDAY</option>
                <option value="TUE">TUESDAY</option>
                <option value="WED">WEDNESDAY</option>
                <option value="THU">THURSDAY</option>
                <option value="FRI">FRIDAY</option>
                <option value="SAT">SATURDAY</option>
            </select>
            <input type="submit" name="action" value="Update Breakfast" onclick="form.action='meal/update';">
            <input type="submit" name="action" value="Update Lunch" onclick="form.action='meal/update';">
        </form:form>

        <form action="dish/add" method="post">
            <h4>Add New Dish</h4>
            <input type="text" name="dishName">
            <button type="submit">Add New Dish</button>
        </form>

        <p><a href="${basePath}/logout">Logout</a></p>
    </div>
    <div class="rightFLoat">
        <table id="theTable" border="1">
            <thead>
            <tr>
                <th>Day</th>
                <th>Break Fast</th>
                <th>Lunch</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>SUNDAY</td>
                <td><c:out value="${((weeklyMeal['SUN'])['B']).getMealDishesAsString()}"/></td>
                <td><c:out value="${((weeklyMeal['SUN'])['L']).getMealDishesAsString()}"/></td>
            </tr>
            <tr>
                <td>MONDAY</td>
                <td><c:out value="${((weeklyMeal['MON'])['B']).getMealDishesAsString()}"/></td>
                <td><c:out value="${((weeklyMeal['MON'])['L']).getMealDishesAsString()}"/></td>
            </tr>
            <tr>
                <td>TUESDAY</td>
                <td><c:out value="${((weeklyMeal['TUE'])['B']).getMealDishesAsString()}"/></td>
                <td><c:out value="${((weeklyMeal['TUE'])['L']).getMealDishesAsString()}"/></td>
            </tr>
            <tr>
                <td>WEDNESDAY</td>
                <td><c:out value="${((weeklyMeal['WED'])['B']).getMealDishesAsString()}"/></td>
                <td><c:out value="${((weeklyMeal['WED'])['L']).getMealDishesAsString()}"/></td>
            </tr>
            <tr>
                <td>THURSDAY</td>
                <td><c:out value="${((weeklyMeal['THU'])['B']).getMealDishesAsString()}"/></td>
                <td><c:out value="${((weeklyMeal['THU'])['L']).getMealDishesAsString()}"/></td>
            </tr>
            <tr>
                <td>FRIDAY</td>
                <td><c:out value="${((weeklyMeal['FRI'])['B']).getMealDishesAsString()}"/></td>
                <td><c:out value="${((weeklyMeal['FRI'])['L']).getMealDishesAsString()}"/></td>
            </tr>
            <tr>
                <td>SATURDAY</td>
                <td><c:out value="${((weeklyMeal['SAT'])['B']).getMealDishesAsString()}"/></td>
                <td><c:out value="${((weeklyMeal['SAT'])['L']).getMealDishesAsString()}"/></td>
            </tr>
            </tbody>
        </table>
        <br>

    </div>
    <br>

    <p>
</div>




</body>
</html>

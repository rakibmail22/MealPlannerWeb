<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${path}/jsp/style/common.css">
</head>
<body>

<div>
    <div class="leftFloat">
        <form>
            <table border="1">
                <thead>
                <tr>
                    <th>All Dishes</th>
                </tr>
                </thead>
                <c:forEach var="dish" items="${allDishes}" varStatus="loop">
                    <tr>
                        <td>
                            <label><input type="checkbox" name="selectedDishes" value="${loop.index}">${dish.name}
                            </label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="Create New Breakfast" onclick="form.action='createNewBreakfast';">
            <input type="submit" value="Create New Lunch" onclick="form.action='createNewLunch';">
        </form>

        <form action="addNewDish" method="get">
            <h4>Add New Dish</h4>
            <input type="text" name="dishName">
            <button type="submit">Add New Dish</button>
        </form>

        <p><a href="${path}/logout">Logout</a></p>
    </div>
    <div class="rightFLoat">
        <form action="deleteMeal" method="get">
            <table border="1">
                <thead>
                <tr>
                    <th>Available Meals</th>
                    <th>Meal Type</th>
                </tr>
                </thead>
                <c:forEach var="meal" items="${allMeals}" varStatus="loop">
                    <tr>
                        <td>
                            <label><input type="checkbox" name="selectedMeals"
                                          value="${loop.index}">${meal.getMealDishesAsString()}
                            </label>
                        </td>
                        <td>
                            <c:out value="${meal.getType()}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit">Delete Meal</button>
        </form>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
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

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
        <form action="createNewMeal" method="get">
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
            <button type="submit">Create New Meal</button>
        </form>
    </div>
    <div class="rightFLoat">
        <form action="deleteMeal" method="get">
            <table border="1">
                <thead>
                <tr>
                    <th>Available Meals</th>
                </tr>
                </thead>
                <c:forEach var="meal" items="${allMeals}" varStatus="loop">
                    <tr>
                        <td>
                            <label><input type="checkbox" name="selectedMeals" value="${loop.index}">${meal.getMealDishesAsString()}
                            </label>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit">Delete Meal</button>
        </form>
    </div>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: bashir
  Date: 10/24/16
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        #theTable {
            border-collapse: collapse;
            border: 1px solid;
        }

        #theTable td {
            border: 1px solid;
        }

        #theTable th {
            border: 1px solid;
        }
    </style>

    <title>Home</title>
</head>
<body>
<h1>MealPlanner Home</h1>

<h2>Welcome <c:out value="${user.name}"/></h2>

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

<p><a href="logout">Logout</a></p>
</body>
</html>

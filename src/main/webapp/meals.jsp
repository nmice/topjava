<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HOME
  Date: 11.12.2018
  Time: 5:36
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <title>Meals</title>

    <style>
        <%--no impotrant that its not colored its working normal!!! Styles--%>
        .normal {
            color: green;
        }
        .exceeded {
            color: red;
        }
    </style>

</head>

<body>

<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

<table border="1" cellpadding="5" cellspacing="2">

    <thead>
    <tr>
        <th>Описание</th>
        <th>Кол-во калорий</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${mealList}" var="meal">
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
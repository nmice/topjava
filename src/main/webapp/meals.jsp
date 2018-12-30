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
</head>

<body>
<style>
    .normal {
        color: green;
    }

    .exceeded {
        color: red;
    }
</style>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

<table border="1" cellpadding="5" cellspacing="2">

    <thead>
    <tr>
        <th>id</th>
        <th>Дата</th>
        <th>Описание</th>
        <th>Кол-во калорий</th>
        <th>Удалить:</th>
        <th>Обновить:</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${meals}" var="meal">
        <td class="${meal.exceed ? 'exceeded' : 'normal'}">${meal.id}</td>
        <td class="${meal.exceed ? 'exceeded' : 'normal'}">${meal.date}</td>
        <td class="${meal.exceed ? 'exceeded' : 'normal'}">${meal.description}</td>
        <td class="${meal.exceed ? 'exceeded' : 'normal'}">${meal.calories}</td>
        <td class="${meal.exceed ? 'exceeded' : 'normal'}"><a href="meals?action=edit&userid=<c:out value="${meal.id}"/>">Update</a></td>
        <td class="${meal.exceed ? 'exceeded' : 'normal'}"><a href="meals?action=delete&userid=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br>
<th2><b>ADD/Edit meal</b></th2>
<br>
<form method="POST" action='meals' name="frmAddUser">
    User ID : <input type="text" readonly="readonly" name="userid"
                     value="<c:out value="${meal.userid}" />" /> <br />
    Прием пищи: <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />"/> <br/>
    Каллории : <input
        type="text" name="calories"
        value="<c:out value="${meal.calories}" />"/> <br/>
    Дата : <input
        type="date" name="dateTime"
        value="<javatime:format pattern="YYYY-MM-dd HH:mm" value="${meal.dateTime}"/>"/> <br/>
    <input
            type="submit" value="ADD"/>
</form>
</div>

</body>
</html>














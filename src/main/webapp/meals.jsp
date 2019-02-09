<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<table>
    <tr>
        <td width="100"><h3><a href="index.html">Home</a></h3></td>
        <td width="100"><h3><a href="?add=true">Add meal</a></h3></td>
    </tr>
</table>
<hr>

<table>
    <thead>
    <tr align="left">
        <th width="300">Дата</th>
        <th width="200">Описание</th>
        <th width="200">Кол-во калорий</th>
        <th width="200">Edit</th>
        <th width="200">Delete</th>
    </tr>
    </thead>
            <%-- table data--%>
        <c:forEach items="${meals}" var="meal">
            <%--set color--%>
            <c:choose>
                <c:when test="${!meal.exceed}">
                    <c:set var="color" value="green"/>
                </c:when>
                <c:otherwise>
                    <c:set var="color" value="red"/>
                </c:otherwise>
            </c:choose>
            <%-- set horizontal line--%>
            <tr>
                <td colspan="5">
                    <hr>
                </td>
            </tr>
            <tr style="color: ${color}">
                <td>${meal.getDate()} ${meal.getTime()}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                    <%-- editing. Add id to url for getting it in method doGet()--%>
                <td><a href="?id=${meal.getId()}">edit</a></td>
                    <%-- deleting. Add id to url for getting it in method doGet()--%>
                <td><a href="?id=${meal.getId()}&del=true">delete</a></td>
            </tr>
        </c:forEach>
    </table>


<%@ include file="form.jsp" %>

</body>
</html>
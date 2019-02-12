<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.01.2019
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editing</title>
</head>

<body>
<div>
    Set meal properties<br>
    <form action="mealServlet" method="get">
        <table>
            <tr>
                <td>Date</td>
                <td><input type="text" name="date"></td>
                <input type="hidden" name="add" value="true">
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description"></td>
            </tr>
            <tr>
                <td>Calories</td>
                <td><input type="text" name="calories"></td>
            </tr>
        </table>
        <input type="submit" value="OK" class="close">
        <a href="#" class="close">Cancel</a>
    </form>
</div>
</body>
</html>
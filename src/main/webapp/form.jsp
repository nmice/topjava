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
        <input type="hidden" name="formtype" value=<%=request.getParameter("formtype")%>>
        <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
        <table>
            <tr>
                <td>Date</td>
                <td><input type="text" name="date"></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type=" text" name="description"></td>
            </tr>
            <tr>
                <td>Calories</td>
                <td><input type=" text" name="calories"></td>
            </tr>
        </table>
        <input type="submit" value="OK" class="close">
        <input type="button" value="Cancel" onClick='location.href="/mealServlet"'>
    </form>
</div>
</body>
</html>
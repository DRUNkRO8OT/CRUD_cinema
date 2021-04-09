<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<body>

<div style="text-align: center;">

    <h2>Session Info</h2>

    <br>

    <table align="center" cellpadding="10">
        <tr>
            <td align="left">
                <form:form action="saveSession" modelAttribute="session">

                    <form:hidden path="id"/>

                    Movie <form:select path="movieId">
                    <c:forEach var="mov" items="${allMovies}">
                        <form:option value="${mov.id}" label="${mov.title}"/>
                    </c:forEach>
                    </form:select>
                    <br><br>

                    Hall <form:select path="hallId">
                    <c:forEach var="hll" items="${allHalls}">
                        <form:option value="${hll.id}" label="${hll.hallNumber}"/>
                    </c:forEach>
                    </form:select>
                    <br><br>

                    Date <form:input path="startTime"/>
                    <br><form:errors path="startTime"/><br>
                    Price <form:input path="price"/>
                    <br><form:errors path="price"/><br>
                    Sold tickets <form:input path="soldTickets"/>
                    <br><form:errors path="soldTickets"/><br>
                    <input type="submit" value="OK">
                </form:form>
            </td>
        </tr>
    </table>

</div>

</body>

</html>
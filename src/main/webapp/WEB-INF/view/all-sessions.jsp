<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>

<body>

<div style="text-align: center;">

    <h2>Timetable</h2>
    <a href="../CRUD_cinema"> Return to menu</a>
    <br><br><br>

    <table border="3" align="center">

        <tr>
            <th>Movie title</th>
            <th>Hall number</th>
            <th>Date</th>
            <th>Price</th>
            <th>Sold tickets</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="session" items="${allSessions}">

            <c:url var="updateButton" value="/updateSession">
                <c:param name="sessionId" value="${session.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteSession">
                <c:param name="sessionId" value="${session.id}"/>
            </c:url>

            <tr>
                <td>${session.movie.title}</td>
                <td>${session.hall.hallNumber}</td>
                <td>
                    <fmt:formatDate value="${session.startTime}" pattern="yyyy-MM-dd HH:mm" />
                </td>
                <td>${session.price}</td>
                <td>${session.soldTickets}</td>
                <td>
                    <input type="button" value="Update"
                           onclick="window.location.href = '${updateButton}'"/>
                    <input type="button" value="Delete"
                           onclick="if (confirm('Are you sure?')) window.location.href = '${deleteButton}'"/>
                </td>
            </tr>

        </c:forEach>

    </table>

    <br>
    <input type="button" value="add"
           onclick="window.location.href = 'addNewSession'"/>

</div>

</body>

</html>
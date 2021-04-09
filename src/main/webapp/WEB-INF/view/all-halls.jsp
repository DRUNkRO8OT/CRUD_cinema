<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<body>

<div style="text-align: center;">

    <h2>All halls</h2>
    <a href="../CRUD_cinema"> Return to menu</a>
    <br><br><br>

    <table border="3" align="center">

        <tr>
            <th>Number</th>
            <th>Name</th>
            <th>Capacity</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="hall" items="${allHalls}">

            <c:url var="updateButton" value="/updateHall">
                <c:param name="hallId" value="${hall.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteHall">
                <c:param name="hallId" value="${hall.id}"/>
            </c:url>

            <tr>
                <td>${hall.hallNumber}</td>
                <td>${hall.hallName}</td>
                <td>${hall.capacity}</td>
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
           onclick="window.location.href = 'addNewHall'"/>

</div>

</body>

</html>
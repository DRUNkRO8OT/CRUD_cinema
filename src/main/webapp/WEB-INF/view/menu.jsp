<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<body>

<div style="text-align: center;">

    <br><br>

    <table border="3" align="center">
        <tr>
            <th>Menu</th>
        </tr>

        <tr>
            <td>
                <input type="button" style="height:50px;width:100px" value="All movies"
                       onclick="window.location.href = '<c:url value="/showAllMovies"/>'"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" style="height:50px;width:100px" value="All halls"
                       onclick="window.location.href = '<c:url value="/showAllHalls"/>'"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" style="height:50px;width:100px" value="Timetable"
                       onclick="window.location.href = '<c:url value="/showAllSessions"/>'"/>
            </td>
        </tr>

    </table>

</div>

</body>

</html>
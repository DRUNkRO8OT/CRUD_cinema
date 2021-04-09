<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>

<body>

<div style="text-align: center;">

    <h2>All movies</h2>
    <a href="../CRUD_cinema"> Return to menu</a>
    <br><br><br>
    <a href="../CRUD_cinema/report"> Make reports</a>
    <br><br><br>

    <table border="3" align="center">

        <tr>
            <th>Title</th>
            <th>Country</th>
            <th>Director</th>
            <th>Genre</th>
            <th>Duration</th>
            <th>Operations</th>
        </tr>

        <c:forEach var="movie" items="${allMovies}">

            <c:url var="updateButton" value="/updateMovie">
                <c:param name="movieId" value="${movie.id}"/>
            </c:url>

            <c:url var="deleteButton" value="/deleteMovie">
                <c:param name="movieId" value="${movie.id}"/>
            </c:url>

            <tr>
                <td>${movie.title}</td>
                <td>${movie.country}</td>
                <td>${movie.director}</td>
                <td>${movie.genre}</td>
                <td>${movie.duration} min.</td>
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
               onclick="window.location.href = 'addNewMovie'"/>

</div>

</body>

</html>
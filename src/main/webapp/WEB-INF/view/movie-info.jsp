<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<body>

<div style="text-align: center;">

    <h2>Movie Info</h2>

    <br>

    <table align="center" cellpadding="10">
        <tr>
            <td align="left">
                <form:form action="saveMovie" modelAttribute="movie">

                    <form:hidden path="id"/>

                    Title <form:input path="title"/>
                    <br><form:errors path="title"/><br>
                    Country <form:input path="country"/>
                    <br><br>
                    Director <form:input path="director"/>
                    <br><br>
                    Genre <form:input path="genre"/>
                    <br><br>
                    Duration <form:input path="duration"/> min.
                    <br><br>
                    <input type="submit" value="OK">
                </form:form>
            </td>
        </tr>
    </table>

</div>

</body>

</html>
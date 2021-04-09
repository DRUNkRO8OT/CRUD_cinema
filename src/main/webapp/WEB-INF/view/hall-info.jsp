<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html>

<body>

<div style="text-align: center;">

    <h2>Hall Info</h2>

    <br>

    <table align="center" cellpadding="10">
        <tr>
            <td align="left">
                <form:form action="saveHall" modelAttribute="hall">

                    <form:hidden path="id"/>

                    Number <form:input path="hallNumber"/>
                    <br><form:errors path="hallNumber"/><br>
                    Name <form:input path="hallName"/>
                    <br><br>
                    Capacity <form:input path="capacity"/>
                    <br><br>
                    <input type="submit" value="OK">
                </form:form>
            </td>
        </tr>
    </table>

</div>

</body>

</html>
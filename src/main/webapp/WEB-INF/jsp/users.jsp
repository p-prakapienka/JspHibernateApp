<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

    <section>
        <h1>Users</h1>
        <p><a href="users?action=create">Create</a></p>

        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <jsp:useBean id="user" scope="page" type="by.prakapienka.at13java.model.User"/>
                <tr>
                    <td>${user.id}</td>
                    <td><a href="orders?id=${user.id}">${user.name}</a></td>
                    <td><a href="users?action=update&id=${user.id}">Update</a></td>
                    <td><a href="users?action=delete&id=${user.id}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>

</body>
</html>

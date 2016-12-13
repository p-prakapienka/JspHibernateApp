<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h1>Orders</h1>
    <p><a href="orders?action=create&id=${id}">Create</a></p>

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
        <c:forEach items="${orders}" var="order">
            <jsp:useBean id="order" scope="page" type="by.prakapienka.at13java.model.Order"/>
            <tr>
                <td>${order.id}</td>
                <td><a href="items?id=${id}&order=${order.id}">${order.name}</a></td>
                <td><a href="orders?action=update&id=${id}&order=${order.id}">Update</a></td>
                <td><a href="orders?action=delete&id=${id}&order=${order.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><a href="users">Back</a></p>
</section>

</body>
</html>

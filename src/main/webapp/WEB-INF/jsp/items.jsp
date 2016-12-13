<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h1>Order items</h1>
    <h2>Order " + ${order}</h2>
    <p><a href="items?action=add&id=${id}&order=${order}">Add</a></p>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="item">
            <jsp:useBean id="item" scope="page" type="by.prakapienka.at13java.model.OrderItem"/>
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td><a href="items?action=delete&id=${id}&order=${order}&item=${item.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p><a href="orders?id=${id}">Back</a></p>
</section>

</body>
</html>

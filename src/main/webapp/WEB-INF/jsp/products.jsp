<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h1>Products</h1>
    <p><a href="products?action=create">Create</a></p>

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
        <c:forEach items="${products}" var="product">
            <jsp:useBean id="product" scope="page" type="by.prakapienka.at13java.model.OrderItem"/>
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td><a href="products?action=update&id=${product.id}">Update</a></td>
                <td><a href="products?action=delete&id=${product.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

</body>
</html>

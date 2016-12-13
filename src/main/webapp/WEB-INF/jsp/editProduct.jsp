<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<h3>${param.action == 'create' ? 'Create user' : 'Edit user'}</h3>

<jsp:useBean id="product" type="by.prakapienka.at13java.model.OrderItem" scope="request"/>
<form method="post" action="products">
    <input type="hidden" name="id" value="${product.id}">
    <label>Name:
        <input name="name" value="${product.name}"/>
    </label>
    <input type="submit" value="Ok"/>
</form>
<p><a href="products">Back</a></p>
</body>
</html>

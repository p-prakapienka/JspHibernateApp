<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<h3>${param.action == 'create' ? 'Create Order' : 'Edit Order'}</h3>

<jsp:useBean id="order" type="by.prakapienka.at13java.model.Order" scope="request"/>
<form method="post" action="orders">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="order" value="${order.id}">
    <label>Name:
        <input name="name" value="${order.name}"/>
    </label>
    <input type="submit" value="Ok"/>
</form>
<p><a href="orders?id=${id}">Back</a></p>
</body>
</html>

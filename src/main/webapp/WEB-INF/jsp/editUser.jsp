<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

    <h3>${param.action == 'create' ? 'Create user' : 'Edit user'}</h3>

    <jsp:useBean id="user" type="by.prakapienka.at13java.model.User" scope="request"/>
    <form method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <label>Name:
            <input name="name" value="${user.name}"/>
        </label>
        <input type="submit" value="Ok"/>
    </form>
    <p><a href="users">Back</a></p>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Budget planner</title>
</head>
<body>
 <form:form method="post" modelAttribute="loginData">
  <p><form:input path="email" placeholder="email"/></p>
  <p><form:input path="password" type="password" placeholder="password"/></p>
  <p>${errorData}</p>
  <a href="${pageContext.request.contextPath}/register">register</a>
  <input type="submit" value="Log in"/>
 </form:form>
</body>
</html>
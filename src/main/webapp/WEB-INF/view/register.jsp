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
 <form:form method="post" modelAttribute="user">
  <p><form:input path="username" placeholder="username"/></p>
  <form:errors path="username"/>
  <p><form:input path="email" placeholder="email"/></p>
  <form:errors path="email"/>
  ${errorEmail}
  <p><form:input path="password" type="password" placeholder="password"/></p>
  <form:errors path="password"/>
  <p><input type="submit" value="Register"/></p>
 </form:form>
</body>
</html>
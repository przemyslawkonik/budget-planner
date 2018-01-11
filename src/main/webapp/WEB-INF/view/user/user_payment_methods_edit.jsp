<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
<title>Budget planner</title>
</head>
<body>
 <%@ include file="../jspf/user/user_info.jspf" %>
 <%@ include file="../jspf/user/user_bar.jspf" %>
 <%@ include file="../jspf/menu/menu_bar.jspf" %>
 <p>Edit payment methods</p>
 <form:form method="post" modelAttribute="user">
  <form:checkboxes path="paymentMethods" items="${methods}" itemValue="id" itemLabel="name"/>
  <form:hidden path="username"/>
  <form:hidden path="email"/>
  <form:hidden path="password"/>
  <form:hidden path="cash"/>
  <form:hidden path="accountValue"/>
  <input type="submit" value="submit"/>
 </form:form>
</body>
</html>
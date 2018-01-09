<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Budget planner</title>
</head>
<body>
 <%@ include file="../jspf/user/user_info.jspf" %>
 <%@ include file="../jspf/user/user_bar.jspf" %>
 <%@ include file="../jspf/menu/menu_bar.jspf" %>
 <a href="${pageContext.request.contextPath}/categories/add">Add new category</a>
 <%@ include file="../jspf/category/category_table.jspf" %>
</body>
</html>
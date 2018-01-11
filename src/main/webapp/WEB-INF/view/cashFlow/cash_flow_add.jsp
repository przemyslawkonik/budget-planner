<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
<title>Budget planner</title>
</head>
<body>
 <%@ include file="../jspf/user/user_info.jspf" %>
 <%@ include file="../jspf/menu/menu_bar.jspf" %>
 <div class="panel panel-primary">
  <div class="panel-heading text-center"><span>Add cash flow</span></div>
   <div class="panel-body">
   <%@ include file="../jspf/cashFlow/cash_flow_form.jspf" %>
  </div>
 </div>
</body>
</html>
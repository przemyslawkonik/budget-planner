<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
  <div class="panel-heading text-center"><span>Budget ${sessionScope.budget.month}/${sessionScope.budget.year}</span></div>
  <div class="panel-body">
   <%@ include file="../jspf/plan/plan_table.jspf" %>
   <a href="${pageContext.request.contextPath}/plans/add">Add plan</a>
  </div>
 </div>
 <div class="navbar-form navbar-right">
  <form class="navbar-form navbar-left" method="post">
   <input class="btn btn-primary" type="submit" value="create budget"/>
  </form>
 </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
<title>Budget planner</title>
</head>
<body>
 <%@ include file="../jspf/user/user_info.jspf" %>
 <%@ include file="../jspf/menu/menu_bar.jspf" %>
 <div class="panel panel-primary">
  <div class="panel-heading text-center"><span>Budget ${budget.month}/${budget.year} summary</span></div>
  <div class="panel-body">
   <%@ include file="../jspf/budget/budget_summary.jspf" %>
   <p>Money balance: <strong>${user.money}</strong></p>
  </div>
 </div>
</body>
</html>
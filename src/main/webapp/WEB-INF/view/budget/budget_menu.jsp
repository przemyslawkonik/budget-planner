<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
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
  <div class="panel-heading text-center"><span>Budget ${budget.month}/${budget.year} details</span></div>
  <div class="panel-body">
   <%@ include file="../jspf/budget/budget_details.jspf" %>
   <p>Budget reality balance: <strong>${budget.realityBalance}</strong></p>
  </div>
 </div>
 <nav class="navbar navbar-inverse">
  <div class="container-fluid">
   <form class="navbar-form navbar-left" method="post">
    <div class="form-group">
     <input class="form-control" type="number" placeholder="year" name="year"/>
     <input class="form-control" type="number" placeholder="month" name="month"/>
    </div>
    <input class="btn btn-primary" type="submit" class="btn btn-default" value="Find"/>
   </form>
   <div class="navbar-form navbar-right">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/budgets/new">Create new budget</a>
   </div>
  </div>
</nav>
</body>
</html>
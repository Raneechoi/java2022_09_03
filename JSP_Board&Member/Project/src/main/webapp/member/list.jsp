<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body style="text-align: center; margin-top: 20px">
	<table border="1">
		<tr>
			<th>EMAIL</th>
			<th>PASSWORD</th>
			<th>NICK_NAME</th>
			<th>REG_AT</th>
			<th>LAST_LOGIN</th>
		</tr>
		<c:forEach items="${list}" var="mvo"> 
			<tr>
				<td>${mvo.email }</td>
				<td>${mvo.pwd }</td>
				<td>${mvo.nick_name }</td>
				<td>${mvo.reg_at }</td>
				<td>${mvo.last_login }</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/"><button type="button"  class="btn btn-outline-success" style="margin-top: 20px">메인</button></a>

</body>
</html>
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
<body style="text-align: center; margin-top: 20px" >
<form action="/mem/update" method="post">
	EMAIL : <input type="text" name="email" value="${mvo.email }" readonly style="margin-top: 20px"> <br>
	PASSWORD : <input type="text" name="pwd" value="${mvo.pwd }"style="margin-top: 20px"> <br>
	NICK_NAME : <input type="text" name="nick_name" value="${mvo.nick_name }" style="margin-top: 20px"> <br>
	<button type="submit"  class="btn btn-outline-success" style="margin-top: 20px">수정</button>
</form>

</body>
</html>
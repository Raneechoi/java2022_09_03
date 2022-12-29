<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List page</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>PNO</th>
				<th>PNAME</th>
				<th>REGDATE</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="pvo">
				<tr>
					<td>${pvo.pno }</td>
					<td><a href="./detail.pd?pno=${pvo.pno }">${pvo.pname }</a></td>
					<td>${pvo.regdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="./register.pd">Go Product Register Page</a>

</body>
</html>
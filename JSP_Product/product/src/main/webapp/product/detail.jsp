
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail page</title>
</head>


<body>
<table border="1">
 	<tr>
 		<th>PNO</th>
 		<td>${pvo.pno }</td>
 	</tr>
 	<tr>
 		<th>PNAME</th>
 		<td>${pvo.pname }</td>
 	</tr>
 	<tr>
 		<th>PRICE</th>
 		<td>${pvo.price }</td>
 	</tr>
 	<tr>
 		<th>REGDATE</th>
 		<td>${pvo.regdate }</td>
 	</tr>
 	<tr>
 		<th>MADEBY</th>
 		<td>${pvo.madeby }</td>
 	</tr>
</table>
<br>
<a href="./edit.pd?pno=${pvo.pno }">Product Modify</a>
<a href="./remove.pd?pno=${pvo.pno }">Product Remove</a>

</body>
</html>
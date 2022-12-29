<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/bor/update" method="post" enctype="multipart/form-data">
	BNO : <input type="text" name="bno" value="${bvo.bno }" readonly> <br>
	TITLE : <input type="text" name="title" value =${bvo.title }> <br>
	WRITER : <input type="text" name="writer" value="${ses.email }" readonly> <br>
	CONTENT : <textarea cols="30" rows="3" name="content" >${bvo.content }</textarea> <br>
	image_file : <input type="hidden" name="image_file" value="${bvo.image_file }">
				<input type="file" name="new_file" accept="image/png, image/jpg, image/jpeg, image/gif"><br>
	<!-- <button type="submit">등록</button> -->
	<a href="/bor/modify?bno=${bvo.bno }"><button type="submit">수정</button></a>
</form>
	<a href="/bor/remove?bno=${bvo.bno }"><button type="submit">삭제</button></a><br>
	<a href="/bor/list">메인</a>

</body>
</html>
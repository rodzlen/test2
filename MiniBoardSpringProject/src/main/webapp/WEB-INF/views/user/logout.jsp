<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var='root' value='/'></c:url> <!-- wabapp : / -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>
	<script type="text/javascript">
	alert("로그아웃 됨ㅎㅎ")
	location.href='${root}'
	</script>
</body>
</html>
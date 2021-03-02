<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>department.html</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h3 id="top">부서등록</h3>
	<c:set var="url" value="./deptInsert" />
	<c:if test="${not empty deptVO.department_id}">
	</c:if>
	<form action="${url }" method="post" name="frm">
		department_id <input type="number" name="department_id"
			<c:if test="${not empty deptVO.department_id} }">readonly="readonly"</c:if>><br>

		department_name <select name="department_name">
			<c:forEach items="${deptlist }" var="dept">
				<option>${dept.department_name }</option>
			</c:forEach>
		</select><br> 
		
		manager_id <select name="manager_id">
			<c:forEach items="${deptlist }" var="dept">
				<option>${dept.manager_id }</option>
			<c:if test ="${dept.manager_id==empVO.employee_id }">
	             checked="checked" </c:if>
			</c:forEach>
		</select><br> 
		
		location_id
		<c:forEach items="${deptlist }" var="dept">
			<input type="radio" name="location_id"
				value="${dept.location_id }"> ${dept.location_id }
				<c:if test ="${dept.location_id==empVO.location_id }">
	             checked="checked" </c:if>
	             </c:forEach>
		<br><br>

		<button type="submit">등록</button>
		<button type="reset">초기화</button>
	</form>

</body>
</html>

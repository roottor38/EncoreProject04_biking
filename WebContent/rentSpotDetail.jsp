<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
대여소 detail
         <form action="bikingcontroller" method="get">
          <input type="hidden" name="command" value="addRentInfo">
<table border="1">
	<tr>   
		<th>대여소 이름</th><th>자전거 번호</th><th>대여</th>
	</tr>

	<c:forEach items="${requestScope.rentSpot}" var="dataAll"> 
		<tr>
		<td>${dataAll.rentSpotName}</td>
		<td>${dataAll.bikeId}</td>
		<td><input type="submit" name ="bikeId" value=${dataAll.bikeId}></td>
		</tr>

 	</c:forEach> 
</table>
</form>
</body>
</html>
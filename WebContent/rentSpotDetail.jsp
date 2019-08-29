<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>
<style>
#customers {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 50%;
}

#customers td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body>
		
	<form action="bikingcontroller" method="get">
	<input type="hidden" name="command" value="addRentInfo">
	
	
<table id="customers">
  <tr>
    <th>자전거 번호</th>
    <th>대여</th>
  </tr>
 	<c:forEach items="${requestScope.rentSpot}" var="dataAll"> 
		<tr>
		<td>${dataAll.bikeId}</td>
		<td><input type="submit" name ="bikeId" value=${dataAll.bikeId}>
		</td>
		</tr>

 	</c:forEach>
</table>

</form>
</body>
</html>
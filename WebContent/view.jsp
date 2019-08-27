<%@page import="org.apache.catalina.startup.SetAllPropertiesRule"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.dto.RentSpotDTO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
</head>
<body>
	${requestScope.data}<br>
	aaaaa<br>

	<c:forEach items="${requestScope.data}" var="dataAll"> 
		${dataAll.rentSpotName}        ${dataAll.numBike}<br>


 	</c:forEach> 
	
</body>
</html>
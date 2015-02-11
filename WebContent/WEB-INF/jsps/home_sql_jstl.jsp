<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<sql:query var="rs" dataSource="jdbc/spring">
select id, name, text, email from offers
</sql:query>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>

<style type="text/css">
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th {
	text-align: left;
}
</style>
</head>
<body>

	<center>
		<h1>Welcome to the Jungle</h1>
	</center>



	<p />

	<%
		String name = request.getParameter("nombre");
	%>

	<%
		if (name == null) {
	%>

	<b> nombre is missing </b>

	<%
		} else {
	%>

	I got it, your nombre is
	<%=name%>
	<%
		}
	%>

	<p />

	Session:
	<%=session.getAttribute("name")%>
	<p />

	Request:
	<%=request.getAttribute("name")%>
	<p />

	Request (Using SPEL): ${name}
	<p />

	JSTL:
	<c:out value="${name}"></c:out>
	<p />


JNDI Resource Configuration

	<h3>Results</h3>

	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>eMail</th>
			<th>Service</th>
		</tr>



		<c:forEach var="row" items="${rs.rows}">
			<tr>
				<td>${row.id }</td>
				<td>${row.name }</td>
				<td>${row.email }</td>
				<td>${row.text}</td>
			</tr>

		</c:forEach>





	</table>



	<br />


</body>
</html>
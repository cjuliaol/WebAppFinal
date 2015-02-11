<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<p>List of Offers</p>

	<table class="offers">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>eMail</th>
			<th>Service</th>
		</tr>

		<c:forEach var="row" items="${offers}">
			<tr>
				<td>${row.id }</td>
				<td>${row.user.name }</td>
				<td>${row.user.email }</td>
				<td>${row.text}</td>
			</tr>

		</c:forEach>


	</table>


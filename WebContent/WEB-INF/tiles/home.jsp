<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<center>
	<h1>Welcome to the Jungle</h1>
</center>


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
<p/>

  <c:choose>
     <c:when test="${hasOffer}">
        <p>	<a href="${pageContext.request.contextPath}/createoffer">Edit or delete current offer</a> </p>
     </c:when>
  
     <c:otherwise>
           <p>	<a href="${pageContext.request.contextPath}/createoffer">Add a new offer</a> </p>     
     </c:otherwise>
  </c:choose>





<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href="<c:url value='/admin'></c:url>"> Admin</a>
	</p>
</sec:authorize>





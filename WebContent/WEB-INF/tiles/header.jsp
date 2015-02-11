<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery-ui.min.js"></script>

<link
	href="${pageContext.request.contextPath}/static/css/jquery-ui.min.css	"
	rel="stylesheet" type="text/css" />



<a class="title" href="<c:url value='/'  />">Offers</a>

<sec:authorize access="!isAuthenticated()">
	<a class="login" href="<c:url value='/login'  />">Login</a>
</sec:authorize>

<sec:authorize access="isAuthenticated()">

	<a class="logout" id="logout"
		href="<c:url value='/j_spring_security_logout'/>">Log out </a>

</sec:authorize>




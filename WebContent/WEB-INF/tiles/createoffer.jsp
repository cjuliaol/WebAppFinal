<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Offer</title>

<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
	
	function onDelete(event) {
		
		
		var doDelete = confirm("Are you sure want to delete this offer?");
		
		if(doDelete == false) {
			event.preventDefault();	
		}
	}
	
	function onReady() {
		$("#delete").click(onDelete);
	}
	
	$(document).ready(onReady);
	
	</script>

</head>
<body>

	<sf:form method="post"
		action="${pageContext.request.contextPath}/docreate"
		commandName="offer">

		<sf:input type="hidden" name="id" path="id" />

		<table class="formtable">

			<tr>
				<td class="label">Your offer: <sf:textarea class="control"
						path="text" name="text" rows="10" cols="10"></sf:textarea> <br />
					<sf:errors path="text" cssClass="error"></sf:errors>
				</td>
			</tr>
			<tr>
				<td class="label"><input class="control" value="Save advert"
					type="submit" /></td>
			</tr>

			<c:if test="${offer.id != 0 }">

				<tr>
					<td class="label">&nbsp;</td>
				</tr>

				<tr>
					<td class="label"><input name="delete" class="delete control" id="delete"
						value="Delete this advert" type="submit" /></td>
				</tr>


			</c:if>


		</table>

	</sf:form>

</body>
</html>
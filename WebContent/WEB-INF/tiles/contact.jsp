<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Message</title>

<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />

</head>
<body>


	<sf:form method="post" commandName="message">
	
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>

		

		<table class="formtable">

			<tr>
				<td class="label">Your name: <sf:input class="control"
						path="name" name="name" rows="10" cols="10"></sf:input> <br />
					<sf:errors path="name" cssClass="error"></sf:errors>
				</td>
			</tr>

			<tr>
				<td class="label">Your email: <sf:input class="control"
						path="email" name="email" rows="10" cols="10"></sf:input> <br />
					<sf:errors path="email" cssClass="error"></sf:errors>
				</td>
			</tr>

			<tr>
				<td class="label">Subject: <sf:input class="control"
						path="subject" name="subject" rows="10" cols="10"></sf:input> <br />
					<sf:errors path="subject" cssClass="error"></sf:errors>
				</td>
			</tr>

			<tr>
				<td class="label">Your Message: <sf:textarea class="control"
						path="content" name="content" rows="10" cols="10"></sf:textarea> <br />
					<sf:errors path="content" cssClass="error"></sf:errors>
				</td>
			</tr>



			<tr>
				<td class="label"><input class="control" value="Send message"
					type="submit" /></td>
			</tr>




		</table>

	</sf:form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Account</title>

<link href="${pageContext.request.contextPath}/static/css/style.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>

<script type="text/javascript">
	function onLoad() {

		$("#passw").keyup(checkPasswordsMatch);
		$("#confirmpassw").keyup(checkPasswordsMatch);

		$("#details").submit(canSubmit);

	}

	function canSubmit() {
		var password = $("#passw").val();
		var confirmpassword = $("#confirmpassw").val();

		if (password == confirmpassword) {
			return true;
		} else {
			alert("<fmt:message key='UnmatchedPasswords.user.password'/>")
			return false;
		}
	}

	function checkPasswordsMatch() {

		var password = $("#passw").val();
		var confirmpassword = $("#confirmpassw").val();

		if (password.length > 3 || confirmpassword.length > 3) {

			if (password == confirmpassword) {

				$("#matchpass").text(
						"<fmt:message key='MatchedPasswords.user.password'/>");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("error");

				//$("#matchpass").text("Passwords match").addClass("valid").removeClass("error");
			} else {
				$("#matchpass")
						.text(
								"<fmt:message key='UnmatchedPasswords.user.password'/>");
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("valid");
			}

		}

	}

	$(document).ready(onLoad);
</script>


</head>
<body>




	<h1>Create New Account</h1>
	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">

		<table class="formtable">
			<tr>
				<td class="label">Username: <sf:input class="control"
						path="username" name="username" type="text" /> <br /> <sf:errors
						path="username" cssClass="error"></sf:errors>
				</td>
			</tr>

			<tr>
				<td class="label">Name: <sf:input class="control" path="name"
						name="name" type="text" /> <br /> <sf:errors path="name"
						cssClass="error"></sf:errors>
				</td>
			</tr>

			<tr>
				<td class="label">Password: <sf:input id="passw"
						class="control" path="password" name="password" type="password" />
					<br /> <sf:errors path="password" cssClass="error"></sf:errors>
				</td>
			</tr>

			<tr>
				<td class="label">Confirm password: <input id="confirmpassw"
					class="control" name="confirmpassword" type="password" />
					<div id="matchpass"></div>
				</td>
			</tr>

			<tr>
				<td class="label">Email: <sf:input class="control" path="email"
						name="email" type="text" /> <br /> <sf:errors path="email"
						cssClass="error"></sf:errors>
				</td>
			</tr>

			<tr>
				<td class="label"><input class="control" value="Create account"
					type="submit" /></td>
			</tr>

		</table>

	</sf:form>

</body>
</html>
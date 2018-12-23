<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
	$(document).ready(function() {

		$("form").validate({

			rules : {
				userName : {
					required : true
				},
				password : {
					required : true
				}
			},
			messages : {
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>
<style>
.tnFormat {
	background: none repeat scroll 0 0 #272525;
	border-color: #EEEEEE;
	border-radius: 0;
	padding: 5px;
	text-color: white;
	heigt: auto;
}

.labelForm {
	color: #FFFFFF;
	font-weight: bold;
}

.login-bttn {
	background-color: #272525 !important;
}

.error {
	color: red;
	font-weight: 200;
}
</style>


<div class="row">

	<div class="col-md-3"></div>
	<div class="col-md-6">

		<div class="thumbnail tnFormat">
			<img src="img/GoldenAge.jpg" class="img-rounded">
			<div class="caption">

				<c:url var="formAction" value="/login" />

				<form method="POST" action="${formAction}">
					<input type="hidden" name="destination"
						value="${param.destination}" /> <input type="hidden"
						name="CSRF_TOKEN" value="${CSRF_TOKEN}" />


					<div class="form-group">
						<label class="labelForm" for="userName">User Name: </label> <input type="text"
							id="userName" name="userName" placeHolder="User Name"
							class="form-control" />
					</div>
					<div class="form-group">
						<label class="labelForm" for="password">Password: </label> <input type="password"
							id="password" name="password" placeHolder="Password"
							class="form-control" />
					</div>
					<button id = "LoginButton" type="submit" class="btn-danger btn-block btn btn-default login-bttn">Login</button>

				</form>
			</div>
		</div>
	</div>
</div>



<c:import url="/WEB-INF/jsp/footer.jsp" />
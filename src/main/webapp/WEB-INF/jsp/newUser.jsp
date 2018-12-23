<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$.validator.addMethod('capitals', function(thing) {
							return thing.match(/[A-Z]/);
						});
						$("form")
								.validate(
										{

											rules : {
												userName : {
													required : true
												},
												password : {
													required : true,
													minlength : 15,
													capitals : true,
												},
												confirmPassword : {
													required : true,
													equalTo : "#password"
												},
												emailAddress : {
													email : true,
													required : true,
												},
												firstName : {
													required : true,
												},
												lastName : {
													required : true,
												}
											},
											messages : {
												password : {
													minlength : "Password too short, make it at least 15 characters",
													capitals : "Field must contain a capital letter",
												},
												confirmPassword : {
													equalTo : "Passwords do not match"
												},
												emailAddress : {
													required : "You must provide an email address"
												},
												firstName : {
													required : "You must enter your first name"
												},
												lastName : {
													required : "You must enter your last name"
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
		<c:url var ="goldenAgeRef" value = "/img/GoldenAge2.jpg"/> 
		<img src ="${goldenAgeRef }" class="img-responsive"/>
			<div class="caption">
				<c:url var="formAction" value="/users" />
				<form method="POST" action="${formAction}">
					<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />


					<div class="form-group">
						<label class="labelForm" for="userName">User Name: </label> <input type="text"
							id="userName" name="userName" placeHolder="User Name"
							class="form-control" />
					</div>
					
					<div class="form-group">
						<label class="labelForm" for="emailAddress">Email Address: </label> <input type="text"
							id="emailAddress" name="emailAddress" placeHolder="Email Address"
							class="form-control" />
					</div>
					
					<div class="form-group">
						<label class="labelForm" for="firstName">First Name: </label> <input type="text"
							id="firstName" name="firstName" placeHolder="Frist Name"
							class="form-control" />
					</div>
					
					<div class="form-group">
						<label class="labelForm" for="lastName">Last Name: </label> <input type="text"
							id="lastName" name="lastName" placeHolder="Last Name"
							class="form-control" />
					</div>
					
					<div class="form-group">
						<label class="labelForm" for="password">Password: </label> <input type="password"
							id="password" name="password" placeHolder="Password"
							class="form-control" />
					</div>
					<div class="form-group">
						<label class="labelForm" for="confirmPassword">Confirm Password: </label> <input
							type="password" id="confirmPassword" name="confirmPassword"
							placeHolder="Re-Type Password" class="form-control" />
					</div>
					<label class="labelForm" id="role" for="role">Select Membership</label><br> <select
						class="form-control" id="role" name="role"
						class="input-sm col-lg-12">
						<option selected="selected" value="Standard">Standard</option>
						<option value="Premium">Premium</option>
					</select><br>
					<button type="submit" class="btn btn-default btn-danger btn-block login-bttn">Create
						User</button>


				</form>
			</div>
		</div>
	</div>
	<div class="col-sm-3"></div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
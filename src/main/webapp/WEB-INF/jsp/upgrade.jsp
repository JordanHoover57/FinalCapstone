<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

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
			
			<div class="jumbotron" style="background-image: url('img/upgrade2.jpg'); background-size: cover; background-position-y: inherit;">
  			<c:set var = "routing" value = "${routing}"/>
  			<c:choose>
  				<c:when test="${routing eq 'collection'}">
  					<h3 class='signup'>You've exceeded your collection limit! Time to upgrade!</h3>
  				</c:when>
  				<c:when test="${routing eq 'comic'}">
  					<h3 class='signup'>You've reached your comic limit! Upgrade to add UNLIMITED COMICS!</h3>
  				</c:when>
  				<c:otherwise>
  					<h3 class='signup'>Answer the call, Upgrade today!</h3>
  				</c:otherwise>
  			</c:choose>
  			<!--<h3 class="signup">Upgrade today!</h3>-->
  			<!-- We can update the message in this first paragraph depending on the reason the user was brought here-->
  			<p class="lead">Upgrade to a premium membership to access additional features!</p>
  			<p>Create unlimited collections!</p>
  			<p>Fill them with unlimited comics!</p>
  			<p class="lead">
    			
  			</p>
		</div>
			
			
			
			<div class="caption">

				<form action="upgrade" method="POST">
					<input type="hidden" name="userName" value="${currentUser.userName }"> 
					<input type="hidden" name="destination" value="${param.destination}" /> 
					<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
					<button type="submit" class="btn-primary btn-lg btn-block login-bttn btn">Upgrade To Premium</button>
				</form>


			</div>
		</div>
	</div>
	<div class="col-sm-3"></div>
</div>



<c:import url="/WEB-INF/jsp/footer.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var = "sitesCssUrl" value = "/css/site.css"/>

<!DOCTYPE html>
<html>
	<head>
		<title>Omnibus Editions</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js "></script>
	    <script src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"></script>
	    
	    
		
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("time.timeago").timeago();
				
				$("#logoutLink").click(function(event){
					$("#logoutForm").submit();
				});
				
				var pathname = window.location.pathname;
				$("nav a[href='"+pathname+"']").parent().addClass("active");
				
			});
			
			
		</script>
	
									
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${sitesCssUrl}" />
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
	<body>
		<header>
			<c:url var="homePageHref" value="/" />
			<c:url var="imgSrc" value="/img/ComicCovers.jpg" />
			<a href="${homePageHref}"><img src="${imgSrc}" class="img-responsive" /></a>
		</header>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<ul class="nav navbar-nav">
					<c:url var="homePageHref" value="/homepage" />
					<li><a href="${homePageHref}">Home</a></li>
					<c:if test="${not empty currentUser}">
						<c:url var="collectionsHref" value="/collections" />
						<li><a href="${collectionsHref}">My Collections</a></li>
						<c:url var="searchHref" value="/search" />
						<li><a id="addComic" href="${searchHref}">Search Comics</a></li>
						<li><a href="#" data-toggle="modal" data-target="#newCollectionModal">Create New Collection</a></li>
						<div class="modal fade" id="newCollectionModal" tabindex="-1" role="dialog" aria-labelledby="collectionModalTitle" aria-hidden="true">
  							<div class="modal-dialog modal-dialog-centered" role="document">
   				 				<div class="modal-content">
      								<div class="modal-header">
       									 <h5 class="modal-title" id="collectionModal">Add a New Collection</h5>
        										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          										<span aria-hidden="true">&times;</span>
        										</button>
      								</div>
      								<div class="modal-body">      								
      								<c:url var = "collectionsHRef" value= "/collections"/>
      								<form id="createCollection" method="POST" action="${collectionsHRef}">
      									<label for=collectionName>Collection Name:</label>
      									<input type="hidden" name="destination"
						value="${param.destination}" /> <input type="hidden"
						name="CSRF_TOKEN" value="${CSRF_TOKEN}" >
      									<input name="collectionName" type="text" id="collectionName">
      									<input name="accessType" type="radio" id="accessTypePublic" value="Public" checked="checked">
      									<label for="accessType">Public</label>
      									<input name="accessType" type="radio" id="accessTypePrivate" value="Private">
      									<label for="accessType">Private</label>
      									<input type="submit" value = "Create Collection" style="display:none">		
        									</form>
        									<hr>
        									<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
        									<button id="collectionSubmit" class="btn btn-primary">Create Collection</button>
        									<div class="error"><p></p></div>
        									<script type = "text/javascript">
        									$(document).ready(function() {
        										
        										$("#createCollection").validate({
        											debug: false,
                                                 rules: {
                                                	 	collectionName: {
                                                	 		required: true,
                                                	 		minlength: 4,
                                                      },
                                                  },
                                                  errorPlacement: function(error, element) {
        											    error.appendTo($('.error'));
        											},   
                                                  messages: {
                                                	 	collectionName: {
                                                	 		required: "Please enter a collection name.",
                                                	 		minlength: "Collection name must be at least 4 characters",
                                                	 		}
                                                  }
                                             });
        										
        										$("#collectionSubmit").on("click", function(e) { 
        											$("#createCollection").submit();
        										});
        									});

        									</script>
        									
      								</div>
    								</div>
  							</div>
						</div>
						
						<c:url var="wishlistHref" value="/wishListDetail?wishListId=${wishListId}" />
						<li><a href="${wishlistHref}">Wish List</a></li>
					</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty currentUser}">
							<c:url var="newUserHref" value="/users/new" />
							<li><a id = "SignUp" href="${newUserHref}">Sign Up</a></li>
							<c:url var="loginHref" value="/login" />
							<li><a id = "LogIn" href="${loginHref}">Log In</a></li>
						</c:when>
						<c:otherwise>
							<c:url var="logoutAction" value="/logout" />
							<form id="logoutForm" action="${logoutAction}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
							</form>
							<li><a id = "currentUser">${currentUser.userName}</a></li>
							<li><a id="logoutLink" href="#">Log Out</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
	<!--	<c:if test="${not empty currentUser}">
			<p id="currentUser">Current User: ${currentUser.userName}</p>
		</c:if>	-->	
		<div class="container">
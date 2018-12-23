<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<c:choose>
<c:when test="${empty currentUser}">
<div class="jumbotron" style="background-image: url('img/batcomputer.jpg'); background-size: cover; background-position-y: inherit;">
  <h1 class="signup">Sign up today!</h1>
  <p class="lead">Register for a free standard account to create collections!</p>
  <p>Organize your collection, build a wish list, and find in-depth information on your favorite comics!</p>
  <p>Become a Premium User to unlock special features and unlimited collections!</p>
  <p class="lead">
    <a class="btn btn-success btn-lg" href="/capstone/users/new" role="button">Sign Up!</a>
  </p>
</div>
</c:when>
<c:when test="${currentUser.role eq 'Premium'}">

<div class="jumbotron" style="background-image: url('img/premiumbanner.jpg'); background-size: cover; background-position-y: inherit;">
  <h2 class="lead">Thank you for choosing a premium membership!</h2>
</div>

</c:when>
<c:otherwise>

<div class="header-row" id="header-row" style="padding: 0px; overflow:hidden; height:150px;">
   <div class="container-fluid" style="padding: 0px; ">
      <div class="row"> 
         <div class="col-xs-12"> 
            <c:url var ="standardBanner" value = "/img/standardbanner7.jpg"/> 
				<img src ="${standardBanner}"  style="width: 100%; height: 100%; position: relative; border-radius: 25px;"/>
				<div  style="position: absolute; top: 8px; left: 50px; color: white; font-weight: 900;font-size: large;">Not a Premium Member? Why not?</div>
				<div  style="position: absolute; top:30px; left: 50px; color: white; font-weight: 900;">Upgrade now and take your collection to the next level!</div>
				<div  style="position: absolute; top:50px; left: 50px; color: white; font-weight: 900;">Unlimited collections with unlimited books per collection!</div>
				<div  style="position: absolute; top: 80px; left: 50px;">
				<a class="btn btn-success btn-lg" href="/capstone/upgrade" role="button">Upgrade to Premium!</a>
				</div>
         </div>     
      </div>
   </div>
</div>


    
    
</c:otherwise>
</c:choose>
<hr>
<c:set var="totalCollections" value = "${totalCollections}"/>
<c:set var="totalComics" value = "${totalComics}" />
<h3>Users have added ${totalComics} comics in ${totalCollections} collections!<a class="next-page" href="publicCollections">
	<button class="btn btn-success" id="view-public">View Public Collections <span class="glyphicon glyphicon-play-circle"></span></button>
	</a></h3>
<hr>
<h2>Hottest Titles</h2>
<div class="row">
	<div class="col-md-12">
		<div class="card-deck">
			<c:forEach var="comic" items="${mostPopular}">
				<div class="card-homepage"><img class="card-img-top" src="${comic.image}"/></div>
			</c:forEach>
		</div>
	</div>
</div>
<hr>
<h2>Newest Arrivals</h2>
<div class="row">
	<div class="col-md-12">
		<div class="card-deck">
			<c:forEach var="comic" items="${mostRecent}">
				<div class="card-homepage"><img class="card-img-top" src="${comic.image}"/></div>
			</c:forEach>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
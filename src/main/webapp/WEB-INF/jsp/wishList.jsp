<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<div class="card-deck">
<div class="card">
<h4 class="card-title">${wishList.wishListName}</h4>
<c:choose>
	<c:when test="${empty comicImage}">
		<img src="img/AddCollection.jpg" class="card-img-bottom">
		<p class="card-text">This wish list doesn't contain any comics yet.</p>
		<a href="search" class="btn btn-primary">Add a comic!</a>
	</c:when>
	<c:otherwise>
		<img src="${comicImage}" class="card-img-bottom">
		<a href="search" class="btn btn-primary">Add a comic!</a>
		<a href="wishListDetail?wishListId=${wishList.wishListId}" class="btn btn-primary">Wish List Details</a>
		
		
		</c:otherwise>
		</c:choose>















</div>
</div>










<c:import url="/WEB-INF/jsp/footer.jsp" />
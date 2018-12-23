<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />
<script type="text/javascript"
	src=" http://localhost:8080/capstone/js/wishList.js?newversion"></script>
   <div class="card-deck">
   <c:forEach var = "comic" items = "${wishListComics}">
   
   <div class="card">
	  <h4 class="card-title">${comic.volumeName}</h4> 
	  <h5>Issue ${comic.issueNumber}</h5>
      <img class="card-img-bottom" src="${comic.image}" />
       <button id="addToCollection" class="btn btn-primary" data-toggle="modal" data-target="#wishlistToCollection" data-comic="${comic.id},${comic.volumeName},${comic.issueNumber},${comic.image},${comic.comicVineId},${comic.volumeId}">Add to Collection</button>
		<form id="delete-comic" action="wishListDetailDelete" method="POST">
					<input type="hidden" name="destination" value="${param.destination}" />
					<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}">
					<input type="hidden" name="comicId" value="${comic.id}"> 
					<input type="hidden" name="wishListId" value="${wishListId}">
					<input type="submit" value="Delete Comic" class="btn btn-danger">
				</form>    
   </div>
   </c:forEach>
   </div>
   
   <div class="modal fade" id="wishlistToCollection">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="collectionModal">Select a
					collection to add to</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div id="csrf" style="display: none">${CSRF_TOKEN}</div>
				<form id="add-to-collection">
					<select name="type" id="wishlistDrop">
						<c:forEach var="collection" items="${userCollections}">
							<option value="${collection.collectionId}">${collection.collectionName}</option>
						</c:forEach>
					</select>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				<button type="button" id="addFromWishlist" class="btn btn-primary" data-dismiss="modal">Add</button>
			</div>
		</div>
	</div>
</div> 
   
   
   
   
   
<c:import url="/WEB-INF/jsp/footer.jsp" />
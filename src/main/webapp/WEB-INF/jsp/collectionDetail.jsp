<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<script type="text/javascript" src="http://localhost:8080/capstone/js/comicDetail.js?newversion"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
<div class="card-deck">

	<c:forEach items="${collectionComics}" var="comic">
		<c:set var="collectionId" value="${collectionId}"/>
		<div class="card">
			<h4 class="card-title">${comic.volumeName}</h4>
			<h5>Issue ${comic.issueNumber}</h5>
			<img class="card-img-bottom zooming imageButt" data-toggle="modal"
				data-target="#comic-selector" src="${comic.image}"data-comic="${comic.id},${comic.volumeName},${comic.issueNumber},${comic.image},${comic.comicVineId},${comic.volumeId},${comic.description},${comic.issueName},${comic.condition}" />
			<div style="padding-top: 9px">
				<%-- <button id="viewDetailsButton" data-comic="${comic.id},${comic.volumeName},${comic.issueNumber},${comic.image},${comic.comicVineId},${comic.volumeId}}" 
				class="btn btn-primary viewDetails" data-toggle="modal"
					data-target="#comic-selector">ViewDetails</button> --%>
				<button class="btn btn-primary editComic" data-toggle="modal"
				data-target="#edit-comic" data-thisComic="${comic.id},${comic.description},${comic.issueName},${comic.image}, ${collectionId}, ${comic.condition}">Edit Comic</button>
			</div>
		</div>
	</c:forEach>
</div>
<div class="modal fade" id="comic-selector">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="collectionModal"></h5>
<!-- 				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button> -->
				<div class="modal-body">
					<div class="row">
						<div class="col-md-6" id="imageCol"></div>
						<table class="col-md-6">
							<tr>
								<th id="volumeIssue"></th>
							</tr>
							<tr id="issueName">
								
							</tr>
							<tr id="description">
								
							</tr>
							<tr id="condition">

							</tr>


						</table>
					</div>
				</div>
			</div>
		</div>
	</div>		
</div>

<div class="modal fade" id="edit-comic">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-body editComicDiv">

			</div>
			</div>
		</div>
		</div>	



<c:import url="/WEB-INF/jsp/footer.jsp" />
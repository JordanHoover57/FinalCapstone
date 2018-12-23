<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/header.jsp" />
<c:url var="sitesCssUrl" value="/css/site.css" />
<script type="text/javascript"
	src=" http://localhost:8080/capstone/js/search.js?newversion"></script>



<div class="container">

	<input type="text" id="comicSearch" name="comicSearch">
	<button id="comicButtonSearch" class="btn btn-danger">Search
		For Comics!</button>
</div>
<nav aria-label="Page navigation example">
	<ul class="pagination">
		<li class="page-item active"><a class="page-link">1</a></li>
		<li class="page-item"><a class="page-link">2</a></li>
		<li class="page-item"><a class="page-link">3</a></li>
		<li class="page-item"><a class="page-link">4</a></li>
		<li class="page-item"><a class="page-link">5</a></li>
		<li class="page-item"><a class="page-link">6</a></li>
		<li class="page-item"><a class="page-link">7</a></li>
		<li class="page-item"><a class="page-link">8</a></li>
		<li class="page-item"><a class="page-link">9</a></li>
		<li class="page-item"><a class="page-link">10</a></li>
	</ul>
</nav>

<div class="container">
	<table class="table">
		<thead>
			<tr>
				<th></th>
				<th class="tH1">Volume</th>
				<th class="tH2">Issue #</th>
				<th class="tH3">Issue Date</th>
			</tr>
		</thead>
		<tbody id="comicsTable"></tbody>
	</table>

</div>

<div class="modal fade" id="collection-selector">
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
					<select name="type" id="collectionDrop">
						<c:forEach var="collection" items="${userCollections}">
							<option value="${collection.collectionId}">${collection.collectionName}</option>
						</c:forEach>
					</select>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
				<button type="button" id="addComic" class="btn btn-primary" data-dismiss="modal">Add</button>
			</div>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
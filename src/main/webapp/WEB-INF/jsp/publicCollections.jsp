<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<div class="row">
	<div class="col-md-12">
		<div class="card-deck">
			<c:forEach var="collection" items="${publicCollections}">
				<div class="card-homepage">
					<h4 class="card-title">${collection.collectionName}</h4>
					<c:choose>
						<c:when test="${empty collection.comicImage}">
							<img src="img/AddCollection.jpg" class="card-img-bottom">
						</c:when>
						<c:otherwise>
							<img src="${collection.comicImage}" class="card-img-bottom">
							<a href="collectionDetail?collectionId=${collection.collectionId}" class="btn btn-primary">Collection Details</a>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
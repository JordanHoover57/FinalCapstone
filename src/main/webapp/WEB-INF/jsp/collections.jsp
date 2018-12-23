<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/jsp/header.jsp" />
<script type="text/javascript" src="http://localhost:8080/capstone/js/comicDetail.js?newversion"></script>
<div class="card-deck">
<c:set var="count" value="0" scope="page" />
<c:forEach var="collection" items="${userCollections}">
<div class="card">
<h4 class="card-title">${collection.collectionName}</h4>



	<c:choose>
	<c:when test="${empty collection.comicImage}">
		<img src="img/AddCollection.jpg" class="card-img-bottom"><br><br>
		<p class="card-text">This collection doesn't contain any comics yet.</p>
		<a href="search" class="btn btn-primary">Add a comic!</a>
	</c:when>
	<c:otherwise>
		<a href="collectionDetail?collectionId=${collection.collectionId}">
			<img src="${collection.comicImage}"  class="card-img-bottom zooming">
		</a><br><br>
		<p class="card-text">${numOfBooks[count] } books<p>
<c:set var="count" value="${count + 1}" scope="page"/>
		<div style="padding-top: 9px">
		<a href="search" class="btn btn-primary">Add a comic!</a>
		
    		<form id="${collection.collectionId}" method="POST" action="collections">
    			<input type="hidden" name="destination" value="${param.destination}" /> 
    			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" >
    			<input type="hidden" name="collectionId" value="${collection.collectionId}">
    			<input class="btn btn-danger" value="Delete Collection" type = "submit">
    		</form>
		</div>
		</c:otherwise>
		</c:choose>
		
		<!--  
		<a href="#" class="btn btn-danger delete-this">Delete Collection</a>
    		<form id="delete-collection" method="POST" action="collections">
    			<input type="hidden" name="destination" value="${param.destination}" /> 
    			<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" >
    			<input type="hidden" name="collectionId" value="${collection.collectionId}">
    		</form>
    		
    		<script type = "text/javascript">
        		$(document).ready(function() {
        			$(".delete-this").on("click", function(e) { 
        				$("#delete-collection").submit();
        			});
        		});
        </script>
        -->
</div>
</c:forEach>

</div>





<c:import url="/WEB-INF/jsp/footer.jsp" />
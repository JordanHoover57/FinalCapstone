var ComicApp = ComicApp || {};

$(document).ready(function () {
	
	
	
	$(".viewDetails, .imageButt").on("click", function (event) {
		
		var c =$(this).data('comic');
		var currentComic = helperBoyNo2(c);
		
		var imageCol = $("#imageCol");
		imageCol.html("");
		var imageCell = $("<td>");
		var coverImage = $("<img>").prop("src", currentComic.image).addClass("img-responsive");
		imageCell.append(coverImage);
		imageCol.append(imageCell);
		
		var header = $("#volumeIssue");
		header.html("");
		var headerCell = $("<th>").text(currentComic.volumeName + "  #"+currentComic.issueNumber);
		header.append(headerCell);
		
		var descriptionCell = $("#description");
		descriptionCell.html("");
		var description = $("<td>").text("Description:  " + currentComic.description);
		descriptionCell.append(description);
		
		var conditionCell = $("#condition");
		conditionCell.html("");
		var condition = $("<td>").text("Condition:  " + currentComic.condition);
		conditionCell.append(condition);
		
	});	
	
		$(".editComic").on("click", function (event) {
			
			var editComicDiv = $(".editComicDiv");
			editComicDiv.html("");
			
			var com = $(this).data('thiscomic');
			var currentComic = helperBoyNo3(com);
			console.log(currentComic.issueDate);
			editComicDiv.append(
				$('<img/>').attr("src", currentComic.image).addClass("img-responsive"),
				$('<form action="collectionDetail" method="POST">').attr("id", "edit-comic-form").append(
						$('<input type="hidden">').attr("name", "comicId").attr("value", currentComic.id),
						$('<input type="hidden">').attr("name", "collectionId").attr("value", currentComic.collection),
						$('<label for="issueName">Issue Name:&nbsp;&nbsp;</label>').addClass('labelForm'),
						$('<input type="text">').attr("name", "issueName").attr("value", currentComic.issueName).addClass('form-control'),
						$('<br>'),
						$('<label for="description">Description:&nbsp;&nbsp;</label>').addClass('labelForm'),
						$('<input type="text">').attr("name", "description").attr("value", currentComic.description).addClass('form-control'),
						$('<br>'),
						$('<label for="condition">Condition:&nbsp;&nbsp;</label>').addClass('labelForm'),
						$('<input type="text">').attr("name", "condition").attr("value", currentComic.condition).addClass('form-control'),
						$('<br>'),
						$('<input type="submit">').attr("value", "Save Changes").addClass('btn-success btn-block btn btn-default login-bttn'),
						$('</form>')).addClass('thumbnail tnFormat').append(
						$('<form action="collectionDetail" method="post">').attr("id", "delete-comic").append(
							$('<input type="hidden">').attr("name", "destination").attr("value", currentComic.destination),
							$('<input type="hidden">').attr("name","CSRF_TOKEN").attr("value", currentComic.csrf),
							$('<input type="hidden">').attr("name", "comicId").attr("value", currentComic.id),
							$('<input type="hidden">').attr("name", "collectionId").attr("value", currentComic.collection),
							$('<input type="submit">').attr("value", "Delete Comic").addClass('btn-danger btn-block btn btn-default login-bttn'),
							$('</form>'))));	
	});	
	
	function helperBoyNo2(currentComic){		
		var split = currentComic.split(',');
		var currentComicObj ={
			"id": split[0],
			"volumeName": split[1],
			"issueNumber": split[2],
			"image": split[3],
			"comicVineId": split[4],
			"volumeId": split[5],
			"description": split[6],
			"issueName": split[7],
			"condition": split[8]
		}
		return currentComicObj;
	}
	
	$('.zooming').hover(function(){
	    $(this).addClass('transitions','box-shadow');
	},	function(){
	    $(this).removeClass('transitions', 'box-shadow');   
	});
	
	function helperBoyNo3(currentComic) {
		var splitter = currentComic.split(',');
		var currenterComicObj = {
			"id": splitter[0],
			"description": splitter[1],
			"issueName": splitter[2],
			"image": splitter[3],
			"collection": splitter[4],
			"condition": splitter[5]
		}
		return currenterComicObj;
	}
	 
});
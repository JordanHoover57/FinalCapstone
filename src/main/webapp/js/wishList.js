var ComicApp = ComicApp || {};

$(document).ready(function () {
	
	   $(document).on("click", "#addToCollection",function(event) {
		   ComicApp.selectedComic = $(this).data('comic');
		   console.log(ComicApp.selectedComic);
		   $("#addFromWishlist").on("click", function(event) {
			  var selectedComicObj = helperBoiiiii(ComicApp.selectedComic);
			  selectedComicObj["comicCollectionId"] = $('#wishlistDrop').find(":selected").val();
			  console.log(selectedComicObj.comicCollectionId);
				
		    $.ajax({
		        url: "http://localhost:8080/capstone/wishListDetail",
		        type: "POST",
		        dataType: "json",
		        	contentType: "application/json",
		    		data: JSON.stringify(selectedComicObj),
		    		processData: false
		    }).done(function (data) {
		        console.log("SUCKSESS !!!!!! "+ data);
		        alert("COMIC ADDED!");
		        
		    }).fail(function (xhr, status, error) {
		        console.log(error);
		        //alert("Oopsie Woopsie!");
		    });
			   			   
		   }); 
		   
	 });
		function helperBoy(selectedComic){
	    	var formattedJson = {
	    			volumeName: selectedComic.volume.name, 
	    			comicVineId: selectedComic.id,
	    			volumeId:  selectedComic.volume.id,
	    			issueNumber: selectedComic.issue_number,
	    			image: selectedComic.image.medium_url,
	    			thumbImage:  selectedComic.image.thumb_url,
	    			comicCollectionId: selectedComic.collectionId   			
	    	}
	    	return formattedJson;
	    }
		function helperBoiiiii(currentComic){		
			var split = currentComic.split(',');
			var currentComicObj ={
				"id": split[0],
				"volumeName": split[1],
				"issueNumber": split[2],
				"image": split[3],
				"comicVineId": split[4],
				"volumeId": split[5],	
			}
			return currentComicObj;
		}
	
	
	
});
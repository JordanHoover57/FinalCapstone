var ComicApp = ComicApp || {};

ComicApp.activeLink = 1;
$(document).ready(function () {
    $("#comicButtonSearch, .pagination li").on("click", function (event) {
    	var $listItems = $('.pagination li');
    	
    	   $listItems.click(function(){
    		   $listItems.removeClass('active');
    		   $(this).addClass('active');  
    		   ComicApp.activeLink=$(this).text();   
    	   });
        var searchQuery = $("#comicSearch").val();
    		
        $.ajax({
            url: "http://comicvine.gamespot.com/api/search?api_key=d5c3bf272b75f5fa0401d13da4bcef89857593da&" +
            		"format=jsonp&limit=500&resources=issue&field_list=id,issue_number,volume,cover_date,image&filter=cover_date:asc&query=" + searchQuery + "&page=" + ComicApp.activeLink,
            method: "GET",
            jsonp: "json_callback",
            dataType: "jsonp",
            	jsonpCallback: "JSON_CALLBACK"
        }).done(function (data) {
            console.log(data);
            ComicApp.searchData = data;
            $("#comicsTable").empty();
            
            for (var i = 0; i < data.results.length; i++) {
            	var rowId = "row" + i;
            	var tableRow = $("<tr>").prop("id", rowId);
          
                var imageCell = $("<td>").addClass("col-sm-2");
                var coverImage = $("<img>").prop("src", data.results[i].image.thumb_url).addClass("img-responsive").addClass("img-thumbnail");
                imageCell.append(coverImage);
                tableRow.append(imageCell);

                var volumeNameCell = $("<td>").text(data.results[i].volume.name);
                var issueCell = $("<td>").text(data.results[i].issue_number);
                var dateCell = $("<td>").text(data.results[i].cover_date);
                
                var addButton = $("<button>").prop({'id': 'button'+i, 'name': 'addButton'}).text("Add to Collection").addClass("btn btn-success").addClass("addButton");
                addButton.attr( {'data-toggle': 'modal', 'data-target': '#collection-selector'});
                var wishListButton = $("<button>").prop({'id': 'button'+i, 'name': 'wishListButton'}).text("Add to WishList").addClass("btn btn-primary").addClass("wishListButton");

                tableRow.append(volumeNameCell);
                tableRow.append(issueCell);
                tableRow.append(dateCell);
                tableRow.append(addButton);
                tableRow.append(wishListButton);
                
                $(".addButton").on("click", function(event) {
            			ComicApp.rowSelected = $(this).attr("id");	
                });
                
                
                $("#comicsTable").append(tableRow);
               
            }            
        }).fail(function (xhr, status, error) {
            console.log(error);
        });

    });
   
  
   $(document).on("click", "#addComic",function(event) {
	    var searchResults = ComicApp.searchData.results;
		var rowIndex = parseInt(ComicApp.rowSelected.substr(6));
		
		var selectedComic = searchResults[rowIndex];
		selectedComic["collectionId"] = $('#collectionDrop').find(":selected").val();
		ComicApp.selectedComic = selectedComic;
		var formattedJson = helperBoy(selectedComic);
		console.log(selectedComic);
    $.ajax({
        url: "http://localhost:8080/capstone/search",
        type: "POST",
        dataType: "json",
        	contentType: "application/json",
    		data: JSON.stringify(formattedJson),
    		processData: false
    }).done(function (data) {
        console.log("SUCKSESS !!!!!! "+ data);
        alert("COMIC ADDED!");
        
    }).fail(function (xhr, status, error) {
        console.log(error);
        //alert("Oopsie Woopsie!");
    });
  });
   $(document).on("click", ".wishListButton",function(event) { 
	   	ComicApp.rowSelected = $(this).attr("id");
	    var searchResults = ComicApp.searchData.results;
		var rowIndex = parseInt(ComicApp.rowSelected.substr(6));
		var selectedComic = searchResults[rowIndex];
		ComicApp.selectedComic = selectedComic;
		var formattedJson = helperBoy(selectedComic);
   $.ajax({
       url: "http://localhost:8080/capstone/wishList",
       type: "POST",
       dataType: "json",
       	contentType: "application/json",
   		data: JSON.stringify(formattedJson),
   		processData: false
   }).done(function (data) {
	   alert("COMIC ADDED!");
	   console.log("SUCKSESS !!!!!! "+ data);
       
       
   }).fail(function (xhr, status, error) {
	   alert("COMIC ADDED TO WISH LIST!");
	   console.log(error);
	   
   });
 });
    
  
    // Front end styling 
    $('th').click(function(){
        var table = $(this).parents('table').eq(0)
        var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
        this.asc = !this.asc
        if (!this.asc){rows = rows.reverse()}
        for (var i = 0; i < rows.length; i++){table.append(rows[i])}
    })
    function comparer(index) {
        return function(a, b) {
            var valA = getCellValue(a, index), valB = getCellValue(b, index)
            return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.toString().localeCompare(valB)
        }
    }
    function getCellValue(row, index){ return $(row).children('td').eq(index).text() }
    
    $(".tH1").mouseenter( function(){
    		$(".tH1").addClass("grow");
    })
    $(".tH1").mouseleave( function(){
    		$(".tH1").removeClass("grow");
    })
    $(".tH2").mouseenter( function(){
    		$(".tH2").addClass("grow");
    })
    $(".tH2").mouseleave( function(){
    		$(".tH2").removeClass("grow");
    })
    $(".tH3").mouseenter( function(){
    		$(".tH3").addClass("grow");
    })
    $(".tH3").mouseleave( function(){
    		$(".tH3").removeClass("grow");
	})
	
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
});
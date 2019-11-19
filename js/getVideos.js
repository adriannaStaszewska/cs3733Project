function getVideos() {
	var xhr = new XMLHttpRequest();
   xhr.open("GET", getVideosURL, true);
   xhr.send();
	   
   console.log("sent video request");
	   
   xhr.onloadend = function () {
	   if (xhr.readyState == XMLHttpRequest.DONE) {
		   console.log ("XHR:" + xhr.responseText);
		   processVideoList(xhr.responseText);
	   } else {
		   processVideoList("N/A");
	   }
   };
}

function processVideoList(result) {
	console.log("res:" + result);
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    console.log(constantJson);
	}
}
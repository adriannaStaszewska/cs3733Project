function getPlaylists() {
	var xhr = new XMLHttpRequest();
	console.log(getPlaylistsURL);
   xhr.open("GET", getPlaylistsURL, true);
   xhr.send();
	   
   console.log("sent remote URLs request");
	   
   xhr.onloadend = function () {
	   if (xhr.readyState == XMLHttpRequest.DONE) {
		   console.log ("XHR:" + xhr.responseText);
		   processPlaylistList(xhr.responseText);
	   } else {
		   processVideoList("N/A");
	   }
   };
}

function processPlaylistList(result) {
	console.log("res:" + result);
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    console.log(constantJson);
	    insertPlaylistRow(constantJson["name"]);
	}
	
	
}

function insertPlaylistRow(rowInput) {
	var table = document.getElementById("playlistTableBody");
	var tr = table.insertRow(table.rows.length);
	
	var td = document.createElement("td");
	td = tr.insertCell();
	var element = document.createElement("P");
	element.innerHTML = rowInput;
	
	td.appendChild(element);
	
}
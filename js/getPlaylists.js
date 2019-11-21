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

function getVideosInPlaylist(name) {
	var data = {};
	data["playlistName"] = name;
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	console.log(getPlaylistsURL);
   xhr.open("POST", getVideosInPlaylistURL, true);
   xhr.send(js);
	   
   console.log("sent get videos in playlist request");
	   
   xhr.onloadend = function () {
	   if (xhr.readyState == XMLHttpRequest.DONE) {
		   console.log ("XHR:" + xhr.responseText);
		   processPlaylistVideos(xhr.responseText);
	   } else {
		   processVideoList("N/A");
	   }
   };
}

function processPlaylistVideos(result) {
	console.log("res:" + result);
	var js = JSON.parse(result);
	
//	for (var i = 0; i < js.list.length; i++) {
//		var constantJson = js.list[i];
//	    console.log(constantJson);
//	    insertPlaylistRow(constantJson["name"]);
//	}
	
	
}

function insertPlaylistRow(rowInput) {
	var table = document.getElementById("playlistTableBody");
	var tr = table.insertRow(table.rows.length);
	
	var td = document.createElement("td");
	td = tr.insertCell(0);
	
	td.onclick = function (){
		console.log(this.parentNode.rowIndex);
		fillPlaylistVideos(this.parentNode.rowIndex - 1);
	}
	var element = document.createElement("P");
	element.innerHTML = rowInput;
	td.appendChild(element);
	
	var td = document.createElement("td");
	td = tr.insertCell(1);
	var trash = document.createElement("input");
	trash.setAttribute("type", "button");
	trash.setAttribute("value", "Trash");
	trash.onclick = function (){
		var name = document.getElementById("playlistsTable").rows[this.parentNode.parentNode.rowIndex].cells[0].innerText;
		handleDeletePlaylist(name);
	}
	td.appendChild(trash);
}



function fillPlaylistVideos(row) {
	var name = document.getElementById("playlistTableBody").rows[row].cells[0].innerText;
	console.log(name);
	getVideosInPlaylist(name)
}


function clearPlaylists(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("playlistsTable");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}
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
	    insertPlaylistRow(constantJson["playlistName"]);
	}
	
	
}

function getVideosInPlaylist(name) {
	var data = {};
	data["playlistName"] = name;
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	console.log(getVideosInPlaylistURL);
   xhr.open("POST", getVideosInPlaylistURL, true);
   xhr.send(js);
	   
   console.log("sent get videos in playlist request");
	   
   xhr.onloadend = function () {
	   if (xhr.readyState == XMLHttpRequest.DONE) {
		   console.log ("XHR:" + xhr.responseText);
		   clearPlaylistVideos();
		   processPlaylistVideos(xhr.responseText);
	   } else {
		   processVideoList("N/A");
	   }
   };
}

function processPlaylistVideos(result) {
	console.log("Process videos in playlist");
	console.log("res:" + result);
	var js = JSON.parse(result);
//	console.log("Length: " + js.list.length);
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
//	    console.log(constantJson);
	    var temp2Array = [];
	    temp2Array.push(constantJson["name"]);
	    temp2Array.push(constantJson["character"]);
	    temp2Array.push(constantJson["sentence"]);
	    temp2Array.push("Trash");
	    temp2Array.push(constantJson["url"])
	    temp2Array.push(constantJson["videoID"])
//	    console.log(temp2Array);
	    insertVideoPlaylistRow(temp2Array);
	}
	
	
}

function insertVideoPlaylistRow(inArray) {
	var table = document.getElementById("videosTablePBody");
	var tr = table.insertRow(table.rows.length);
	
//	var td = document.createElement("td");
//	td = tr.insertCell(0);
//	
//	td.onclick = function (){
//
//	}
//	var element = document.createElement("P");
//	element.innerHTML = rowInput;
//	td.appendChild(element);
//	
//	var td = document.createElement("td");
//	td = tr.insertCell(1);
//	var trash = document.createElement("input");
//	trash.setAttribute("type", "button");
//	trash.setAttribute("value", "Trash");
//	trash.onclick = function (){
//		var name = document.getElementById("playlistsTable").rows[this.parentNode.parentNode.rowIndex].cells[0].innerText;
//		handleDeletePlaylist(name);
//	}
//	td.appendChild(trash);
	
	//--------------------------------------
	for(var c = 0; c < inArray.length; c++){
		var td = document.createElement("td");
		
		td = tr.insertCell(c);
		
		if(c==3) {
			var trash = document.createElement("input");
			trash.setAttribute("type", "button");
			trash.setAttribute("value", "Trash");
			trash.onclick = function (){
//				var id = document.getElementById("videosTable").rows[this.parentNode.parentNode.rowIndex].cells[5].getElementsByTagName("p")[0].innerHTML
//				handleDeleteVideo(id);
			}
			td.appendChild(trash);
		} else if(c == 4){
			td.style = "visibility:hidden;display:none;";
			var url = document.createElement("P");
			var url2 = document.createTextNode(inArray[c]);
			url.append(url2);
			url.setAttribute("id", "par");
//			url.innerHTML = rowArray[c];
			url.style = "display:none;";
			td.appendChild(url);
		}  else if(c == 5){
			td.style = "visibility:hidden;display:none;";
			var id = document.createElement("P");
			var id2 = document.createTextNode(inArray[c]);
			id.append(id2);
			id.setAttribute("id", "par");
//			url.innerHTML = rowArray[c];
			id.style = "display:none;";
			td.appendChild(id);
		} else {
			
			td.onclick = function (){
//				handlePlayModal(this.parentNode.rowIndex);
			}
			
			var element = document.createElement("P");
			element.innerHTML = inArray[c];
			//element.setAttribute("value", tempArray[c]);
			
			td.appendChild(element);
		}
	
	
	}
}

function insertPlaylistRow(rowInput) {
	var table = document.getElementById("playlistTableBody");
	var tr = table.insertRow(table.rows.length);
	
	var td = document.createElement("td");
	td = tr.insertCell(0);
	
	td.onclick = function (){
		var tableTemp = document.getElementById("playlistTableBody");
		var rowId = this.parentNode.rowIndex - 1;
		var rowsNotSelected = tableTemp.getElementsByTagName('tr');
        for (var row = 0; row < rowsNotSelected.length; row++) {
            rowsNotSelected[row].style.backgroundColor = "white";
            rowsNotSelected[row].classList.remove('selected');
        }
        var rowSelected = tableTemp.getElementsByTagName('tr')[rowId];
        rowSelected.style.backgroundColor = "orange";
        rowSelected.className += "selected";
		
		console.log(this.parentNode.rowIndex);
		fillPlaylistVideos(this.parentNode.rowIndex - 1);
		
		document.getElementById("addVideoToPlaylistButton").style.visibility = 'visible';
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

function clearPlaylistVideos(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("videosTableP");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}
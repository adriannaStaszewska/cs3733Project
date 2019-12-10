//send request to get playlists
function getPlaylists() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getPlaylistsURL, true);
	xhr.send();
	   
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processPlaylistList(xhr.responseText);
		} else {
		}
	};
}

//process the playlist return
function processPlaylistList(result) {
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    insertPlaylistRow(constantJson["playlistName"]);
	}
	
	
}

//request list of videos in a playlist
function getVideosInPlaylist(name) {
	var data = {};
	data["playlistName"] = name;
	var js = JSON.stringify(data);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", getVideosInPlaylistURL, true);
	xhr.send(js);
	
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			clearPlaylistVideos();
			processPlaylistVideos(xhr.responseText);
		} else {
		}
	};
}

//process the playlist video return
function processPlaylistVideos(result) {
	var js = JSON.parse(result);
	for (var i = 0; i < js.list.length; i++) {
		try {
			var constantJson = js.list[i];
		    var temp2Array = [];
		    temp2Array.push(constantJson["name"]);
		    temp2Array.push(constantJson["character"]);
		    temp2Array.push(constantJson["sentence"]);
		    temp2Array.push("Trash");
		    temp2Array.push(constantJson["url"])
		    temp2Array.push(constantJson["videoID"])
		    insertVideoPlaylistRow(temp2Array);
		} catch(error) {
			console.error("Error: " + error);
		}
	}
	
	
}

//insert playlist video entry into playlist video table
function insertVideoPlaylistRow(inArray) {
	var table = document.getElementById("videosTablePBody");
	var tr = table.insertRow(table.rows.length);

	for(var c = 0; c < inArray.length; c++){
		var td = document.createElement("td");
		
		td = tr.insertCell(c);
		
		if(c==3) {
			var trash = document.createElement("input");
			trash.setAttribute("type", "button");
			trash.setAttribute("value", "Remove");
			trash.onclick = function (){
				
				//video id
				var id = document.getElementById("videosTableP").rows[this.parentNode.parentNode.rowIndex].cells[5].getElementsByTagName("p")[0].innerHTML;
				
				//playlist name
				var tableTemp = document.getElementById("playlistTableBody");
				var rowsNotSelected = tableTemp.getElementsByTagName('tr');
				var selected = -1;
				for (var row = 0; row < rowsNotSelected.length; row++) {
				    if(rowsNotSelected[row].classList.contains("selected")){
				    	selected = row;
				    }
				}
				var playName = document.getElementById("playlistTableBody").rows[selected].cells[0].innerText;
				
				//row number
				var rowNum = this.parentNode.parentNode.rowIndex - 1;
				
				handleRemoveFromPlaylist(id, playName, rowNum);
			}
			td.appendChild(trash);
		} else if(c == 4){
			td.style = "visibility:hidden;display:none;";
			var url = document.createElement("P");
			var url2 = document.createTextNode(inArray[c]);
			url.append(url2);
			url.setAttribute("id", "par");
			url.style = "display:none;";
			td.appendChild(url);
		}  else if(c == 5){
			td.style = "visibility:hidden;display:none;";
			var id = document.createElement("P");
			var id2 = document.createTextNode(inArray[c]);
			id.append(id2);
			id.setAttribute("id", "par");
			id.style = "display:none;";
			td.appendChild(id);
		} else {
			var element = document.createElement("P");
			element.innerHTML = inArray[c];
			td.appendChild(element);
		}
	
	
	}
}

//insert playlist entry into playlist table
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
        	rowsNotSelected[row].bgColor = "white";
            rowsNotSelected[row].classList.remove('selected');
        }
        var rowSelected = tableTemp.getElementsByTagName('tr')[rowId];
        rowSelected.bgColor = "orange";
        rowSelected.classList.add("selected");

		fillPlaylistVideos(this.parentNode.rowIndex - 1);
		
		document.getElementById("addVideoToPlaylistButton").style.visibility = 'visible';
		document.getElementById("playButton").style.visibility = 'visible';
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

//populate videos from a playlist table
function fillPlaylistVideos(row) {
	var name = document.getElementById("playlistTableBody").rows[row].cells[0].innerText;
	getVideosInPlaylist(name)
}

//clear playlist table
function clearPlaylists(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("playlistsTable");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}

//clear playlist videos table
function clearPlaylistVideos(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("videosTableP");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}

//remove requested video from selected playlist
function handleRemoveFromPlaylist(id, playlistName, rowNum){
	var data = {};
	data["playlistName"] = playlistName;
	data["videoID"] = id;
	data["position"] = rowNum;
	var js = JSON.stringify(data);
	console.log(js);
	
	var xhr = new XMLHttpRequest();
	xhr.open("POST", removeFromPlaylistURL, true);
	xhr.send(js);
	
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			clearPlaylistVideos();
		   
			var tableTemp = document.getElementById("playlistTableBody");
			var rowsNotSelected = tableTemp.getElementsByTagName('tr');
			var selected = -1;
			for (var row = 0; row < rowsNotSelected.length; row++) {
			    if(rowsNotSelected[row].classList.contains("selected")){
			    	selected = row;
			    }
			}
		   fillPlaylistVideos(selected);
		} else {
		}
	};
}
function addVideoToSelectedPlaylist(input) {
	console.log("adding video to playlist");
	
	document.getElementById("playlistAddModal").style.visibility = 'visible';
	document.getElementById("playlistAddModalContent").style.visibility = 'visible';
	document.getElementById("addModal").style.visibility = 'visible';
	document.getElementById("playlistAddModal").style.zIndex = '1';
	document.getElementById("addModal").style.zIndex = '3';
	
	var tableTemp = document.getElementById("playlistTableBody");
	var rowsNotSelected = tableTemp.getElementsByTagName('tr');
	var selected = -1;
	for (var row = 0; row < rowsNotSelected.length; row++) {
	    if(rowsNotSelected[row].classList.contains("selected")){
	    	selected = row;
	    }
	}
	
	var playlistName = document.getElementById("playlistTableBody").rows[selected].cells[0].innerText;
	console.log(playlistName);
	
	getAvailableVideos();
}

function addVideo(input) {
	console.log("Handle add video to playlist button triggered");
    
    var tableTemp = document.getElementById("playlistTableBody");
	var rowsNotSelected = tableTemp.getElementsByTagName('tr');
	var selected = -1;
	for (var row = 0; row < rowsNotSelected.length; row++) {
	    if(rowsNotSelected[row].classList.contains("selected")){
	    	selected = row;
	    }
	}
	
	if(selected == -1){
		return;
	}
	
	var playlistName = document.getElementById("playlistTableBody").rows[selected].cells[0].innerText;
	console.log(playlistName);
	
	var tableTemp = document.getElementById("addVideosTableP");
	var rowsNotSelected = tableTemp.getElementsByTagName('tr');
	var selected = -1;
	for (var row = 0; row < rowsNotSelected.length; row++) {
	    if(rowsNotSelected[row].classList.contains("selected")){
	    	selected = row;
	    }
	}
	
	if(selected == -1){
    	return;
    }
	
	var videoID = document.getElementById("addVideosTableP").rows[selected].cells[4].innerText;
    console.log(videoID);
    
    document.getElementById("playlistAddModal").style.visibility = 'hidden';
    document.getElementById("playlistAddModalContent").style.visibility = 'hidden';
    document.getElementById("addModal").style.visibility = 'hidden';
    document.getElementById("playlistAddModal").style.zIndex = '-1';
    document.getElementById("addModal").style.zIndex = '-1';
    
    addVideoToPlaylist(playlistName, videoID);
}

function closeAddModal(e){
    document.getElementById("playlistAddModal").style.visibility = 'hidden';
    document.getElementById("playlistAddModalContent").style.visibility = 'hidden';
    document.getElementById("addModal").style.visibility = 'hidden';
    document.getElementById("playlistAddModal").style.zIndex = '-1';
    document.getElementById("addModal").style.zIndex = '-1';
}

function getAvailableVideos(){
	
	clearAvailableVideos();
	
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getVideosURL, true);
	xhr.send();
	   
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processVideoListForPlaylist(xhr.responseText);
		} else {
			processVideoListForPlaylist("N/A");
		}
	};
}

//-------------------------


function processVideoListForPlaylist(result) {
	console.log("res:" + result);
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    console.log(constantJson);
	    var tempArray = [];
	    tempArray.push(constantJson["name"]);
	    tempArray.push(constantJson["character"]);
	    tempArray.push(constantJson["sentence"]);
	    tempArray.push(constantJson["url"])
	    tempArray.push(constantJson["videoID"])
	    insertRowToAdd(tempArray);
	}
	
	
}

function insertRowToAdd(rowArray) {
	var table = document.getElementById("addVideosTablePBody");
	var tr = table.insertRow(table.rows.length);
	
	for(var c = 0; c < rowArray.length; c++){
		var td = document.createElement("td");
		
		td = tr.insertCell(c);
		
		if(c == 3){
			td.style = "visibility:hidden;display:none;";
			var url = document.createElement("P");
			var url2 = document.createTextNode(rowArray[c]);
			url.append(url2);
			url.setAttribute("id", "par");
			url.style = "display:none;";
			td.appendChild(url);
		}  else if(c == 4){
			td.style = "visibility:hidden;display:none;";
			var id = document.createElement("P");
			var id2 = document.createTextNode(rowArray[c]);
			id.append(id2);
			id.setAttribute("id", "par");
			id.style = "display:none;";
			td.appendChild(id);
		} else {
			
			td.onclick = function (){
//				handlePlayModal(this.parentNode.rowIndex);
				var tableTemp = document.getElementById("addVideosTableP");
				var rowId = this.parentNode.rowIndex;
				var rowsNotSelected = tableTemp.getElementsByTagName('tr');
		        for (var row = 0; row < rowsNotSelected.length; row++) {
		            rowsNotSelected[row].bgColor = "white";
		            rowsNotSelected[row].classList.remove('selected');
		        }
		        var rowSelected = tableTemp.getElementsByTagName('tr')[rowId];
		        rowSelected.bgColor = "orange";
		        rowSelected.classList.add("selected");
			}
			
			var element = document.createElement("P");
			element.innerHTML = rowArray[c];
			//element.setAttribute("value", tempArray[c]);
			
			td.appendChild(element);
		}
	}
}

function addVideoToPlaylist(playlist, video){
	var data = {};
	data["playlistName"] = playlist;
	data["videoID"] = video;
	var js = JSON.stringify(data);
	console.log(js);
	
	var xhr = new XMLHttpRequest();
	console.log(getVideosInPlaylistURL);
	xhr.open("POST", addVideoToPlaylistURL, true);
	xhr.send(js);
	   
	console.log("sent append video request");
	   
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			clearPlaylistVideos();
			//here
			getVideosInPlaylist(playlist);
//		   	processPlaylistVideos(xhr.responseText);
		} else {
//			processVideoList("N/A");
		}
	};
}

function clearAvailableVideos(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("addVideosTableP");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}

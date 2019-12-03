function handleSearch(e) {
    var form = document.search;
    var character = form.searchBox.value;
    var sentence = form.searchBox2.value;

    var local = form.localCheck.checked;
    var remote = form.remoteCheck.checked;

//    console.log(searchTerms);
    console.log("Local: " + local);
    console.log("Remote: " + remote);
    
    //search local server
    if(local == true) {
    	searchLocal(sentence, character);
    }
    
    //search remote servers
    if(remote == true) {
    	searchRemote(sentence, character);
    }
    

}


function searchLocal(sentence, character) {
    var data = {};
	data["sentSearch"] = sentence;
	data["charSearch"] = character;
	
	var js = JSON.stringify(data);
	console.log(js);
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", searchVideosURL, true);
    
    xhr.send(js);
 	   
    console.log("sent search request");
 	   
    xhr.onloadend = function () {
    	if (xhr.readyState == XMLHttpRequest.DONE) {
    		console.log ("XHR:" + xhr.responseText);
    		clearVideos();
    		processSearch(xhr.responseText);
//    		processVideoList(xhr.responseText);
    	} else {
//    		processVideoList("N/A");
    		console.log("error searching local videos");
		}
    };
}

function searchRemote(sentence, character) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getRemotesURL, true);
	xhr.send();
	   
	console.log("sent remote URLs request");
	   
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processRemoteList(xhr.responseText);
		} else {
//			processRemoteList("N/A");
		}
	};
}

function processRemoteList(result) {
	console.log("res:" + result);
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    console.log(constantJson);
	    getRemotes(constantJson["url"], constantJson[""]);
//	    insertRemoteRow(constantJson["url"]);
	}
	
	
}

function getRemotes(url, apiKey) {
	var data = {};
	data["text"] = sentence;
	data["character"] = character;
	
	var js = JSON.stringify(data);
	console.log(js);
    
//    var xhr = new XMLHttpRequest();
//    xhr.open("POST", searchVideosURL, true);
//    
//    xhr.send(js);
// 	   
//    console.log("sent search request");
// 	   
//    xhr.onloadend = function () {
//    	if (xhr.readyState == XMLHttpRequest.DONE) {
//    		console.log ("XHR:" + xhr.responseText);
//    		processSearch(xhr.responseText);
////    		processVideoList(xhr.responseText);
//    	} else {
////    		processVideoList("N/A");
//    		console.log("error searching remote videos");
//		}
//    };
}

function processSearch(result) {
	console.log("res:" + result);
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    console.log(constantJson);
	    var tempArray = [];
	    tempArray.push(constantJson["name"]);
	    tempArray.push(constantJson["character"]);
	    tempArray.push(constantJson["sentence"]);
	    tempArray.push("Trash");
	    tempArray.push(constantJson["url"])
	    tempArray.push(constantJson["videoID"])
	    insertRow(tempArray);
	}
	
	
}

function insertRow(rowArray){
	var table = document.getElementById("videosTableBody");
	var tr = table.insertRow(table.rows.length);
	
	for(var c = 0; c < rowArray.length; c++){
		var td = document.createElement("td");
		
		td = tr.insertCell(c);
		
		if(c==3) {
			var trash = document.createElement("input");
			trash.setAttribute("type", "button");
			trash.setAttribute("value", "Trash");
			trash.onclick = function (){
				var id = document.getElementById("videosTable").rows[this.parentNode.parentNode.rowIndex].cells[5].getElementsByTagName("p")[0].innerHTML
				handleDeleteVideo(id);
			}
			td.appendChild(trash);
		} else if(c == 4){
			td.style = "visibility:hidden;display:none;";
			var url = document.createElement("P");
			var url2 = document.createTextNode(rowArray[c]);
			url.append(url2);
			url.setAttribute("id", "par");
//			url.innerHTML = rowArray[c];
			url.style = "display:none;";
			td.appendChild(url);
		}  else if(c == 5){
			td.style = "visibility:hidden;display:none;";
			var id = document.createElement("P");
			var id2 = document.createTextNode(rowArray[c]);
			id.append(id2);
			id.setAttribute("id", "par");
//			url.innerHTML = rowArray[c];
			id.style = "display:none;";
			td.appendChild(id);
		} else {
			
			td.onclick = function (){
				handlePlayModal(this.parentNode.rowIndex);
			}
			
			var element = document.createElement("P");
			element.innerHTML = rowArray[c];
			//element.setAttribute("value", tempArray[c]);
			
			td.appendChild(element);
		}
	}
}

function clearVideos(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("videosTable");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}
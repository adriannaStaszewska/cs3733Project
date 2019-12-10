//handles local and remote search
function handleSearch(e) {
    var form = document.search;
    var character = form.searchBox.value;
    var sentence = form.searchBox2.value;

    var local = form.localCheck.checked;
    var remote = form.remoteCheck.checked;

    clearVideos();
    
    //search local server
    if(local == true) {
    	searchLocal(sentence, character);
    }
    
    //search remote servers
    if(remote == true) {
    	searchRemote(sentence, character);
    }
    

}

//search local videos by sentence and character
function searchLocal(sentence, character) {
    var data = {};
	data["sentSearch"] = sentence;
	data["charSearch"] = character;
	
	var js = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", searchVideosURL, true);
    
    xhr.send(js);

    xhr.onloadend = function () {
    	if (xhr.readyState == XMLHttpRequest.DONE) {
    		console.log ("XHR:" + xhr.responseText);
    		clearVideos();
    		processSearch(xhr.responseText);
    	} else {
    		console.log("error searching local videos");
		}
    };
}

//search remote sites by sentence and character
function searchRemote(sentence, character) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getRemotesURL, true);
	xhr.send();
	 
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processRemoteList(xhr.responseText, sentence, character);
		} else {
		}
	};
}

//process return of urls for remote sites
function processRemoteList(result, sentence, character) {
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    getRemotes(constantJson["url"], constantJson["api_key"], sentence, character);
	}
	
	
}

//request remote sites
function getRemotes(url, apiKey, sentence, character) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", url, true);
	xhr.setRequestHeader("x-api-key", apiKey);
	xhr.send();
	   
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processRemoteSearch(xhr.responseText, sentence, character);
		} else {
		}
	};
    
}

//process list of remote videos
function processSearch(result) {
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    var tempArray = [];
	    tempArray.push(constantJson["name"]);
	    tempArray.push(constantJson["character"]);
	    tempArray.push(constantJson["sentence"]);
	    tempArray.push("Trash");
	    tempArray.push(constantJson["url"])
	    tempArray.push(constantJson["videoID"])
	    insertSearchRow(tempArray);
	}
	
	
}

//search through remote videos by sentence and character
function processRemoteSearch(result, sentence, character) {
	var js = JSON.parse(result);
	js = js["segments"];
	for (var i = 0; i < js.length; i++) {
		var constantJson = js[i];
	    
	    var addFlag = false;
	    
	    if((sentence == "" ) && (character != "")){
	    	var charSearch = constantJson["character"];
	    	charSearch = charSearch.toLowerCase();
	    	if(charSearch.indexOf(character.toLowerCase()) != -1){
	    		addFlag = true;
	    	}
	    } else if((character == "") && (sentence != "")) {
	    	var sentSearch = constantJson["text"];
	    	sentSearch = sentSearch.toLowerCase();
	    	if(sentSearch.indexOf(sentence.toLowerCase()) != -1){
	    		addFlag = true;
	    	}
	    } else if((character != "") && (sentence != "")){
	    	var charSearch = constantJson["character"];
	    	charSearch = charSearch.toLowerCase();
	    	var sentSearch = constantJson["text"];
	    	sentSearch = sentSearch.toLowerCase();
	    	if((charSearch.indexOf(character.toLowerCase()) != -1) && (sentSearch.indexOf(sentence.toLowerCase()) != -1)){
	    		addFlag = true;
	    	}
	    } else {
	    	addFlag = true;
	    }
	    
	    if(addFlag == true){
		    var tempArray = [];
		    tempArray.push("");
		    tempArray.push(constantJson["character"]);
		    tempArray.push(constantJson["text"]);
		    tempArray.push("Trash");
		    tempArray.push(constantJson["url"]);
		    tempArray.push("");
		    insertRemoteRow(tempArray);
	    }
	}
	
	
}

//insert video entry into table
function insertSearchRow(rowArray){
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
			url.style = "display:none;";
			td.appendChild(url);
		}  else if(c == 5){
			td.style = "visibility:hidden;display:none;";
			var id = document.createElement("P");
			var id2 = document.createTextNode(rowArray[c]);
			id.append(id2);
			id.setAttribute("id", "par");
			id.style = "display:none;";
			td.appendChild(id);
		} else {
			
			td.onclick = function (){
				handlePlayModal(this.parentNode.rowIndex);
			}
			
			var element = document.createElement("P");
			element.innerHTML = rowArray[c];
			td.appendChild(element);
		}
	}
}

//insert remote video entry into table
function insertRemoteRow(rowArray){
	console.log(rowArray);
	var table = document.getElementById("videosTableBody");
	var tr = table.insertRow(table.rows.length);
	
	for(var c = 0; c < rowArray.length; c++){
		var td = document.createElement("td");
		
		td = tr.insertCell(c);
		
		if(c==3) {
			var element = document.createTextNode("");
			td.appendChild(element);
		} else if(c == 4){
			td.style = "visibility:hidden;display:none;";
			var url = document.createElement("P");
			var url2 = document.createTextNode(rowArray[c]);
			url.append(url2);
			url.setAttribute("id", "par");
			url.style = "display:none;";
			td.appendChild(url);
		}  else if(c == 5){
			td.style = "visibility:hidden;display:none;";
			var id = document.createElement("P");
			var id2 = document.createTextNode(rowArray[c]);
			id.append(id2);
			id.setAttribute("id", "par");
			id.style = "display:none;";
			td.appendChild(id);
		} else {
			
			td.onclick = function (){
				handlePlayModal(this.parentNode.rowIndex);
			}
			
			var element = document.createElement("P");
			element.innerHTML = rowArray[c];
			td.appendChild(element);
		}
	}
}

//clear video table
function clearVideos(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("videosTable");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}
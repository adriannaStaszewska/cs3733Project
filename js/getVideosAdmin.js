function getVideos() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getVideosURL, true);
	xhr.send();
 
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
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    console.log(constantJson);
	    var tempArray = [];
	    tempArray.push(constantJson["name"]);
	    tempArray.push(constantJson["character"]);
	    tempArray.push(constantJson["sentence"]);
	    tempArray.push(constantJson["remotely_accessible"]);
	    tempArray.push("Trash");
	    tempArray.push(constantJson["url"])
	    tempArray.push(constantJson["videoID"])
	    insertRow(tempArray);
	}
	
	
}

function insertRow(rowArray) {
	var table = document.getElementById("videosTableBody");
	var tr = table.insertRow(table.rows.length);
	
	for(var c = 0; c < rowArray.length; c++){
		var td = document.createElement("td");
		
		td = tr.insertCell(c);
		
		if(c==3) {
			var check = document.createElement("input");
			check.setAttribute("type", "checkbox");
			check.setAttribute("id", "check");
			if(rowArray[c]){
				check.checked = true;
				check.setAttribute("value", "1");
			} else {
				check.checked = false;
				check.setAttribute("value", "2");
			}
			
			td.onclick = function (){
				console.log(document.getElementById("videosTable").rows[this.parentNode.rowIndex].getElementsByTagName("p")[4].innerHTML);
				if(this.getElementsByTagName("input")[0].value == 2){
					this.getElementsByTagName("input")[0].value = 1;
					updateRemoteAval(true, document.getElementById("videosTable").rows[this.parentNode.rowIndex].getElementsByTagName("p")[4].innerHTML);
				} else {
					this.getElementsByTagName("input")[0].value = 2;
					updateRemoteAval(false, document.getElementById("videosTable").rows[this.parentNode.rowIndex].getElementsByTagName("p")[4].innerHTML);
				}
			}
			
			td.appendChild(check);
		} else if(c == 4){
			var trash = document.createElement("input");
			trash.setAttribute("type", "button");
			trash.setAttribute("value", "Trash");
			trash.onclick = function (){
				var id = document.getElementById("videosTable").rows[this.parentNode.parentNode.rowIndex].getElementsByTagName("p")[4].innerHTML;
				var name = document.getElementById("videosTable").rows[this.parentNode.parentNode.rowIndex].getElementsByTagName("p")[0].innerHTML;
				handleDeleteVideo(name, id);
			}
			td.appendChild(trash);
		} else if(c == 5){
			td.style = "visibility:hidden;display:none;";
			var url = document.createElement("P");
			var url2 = document.createTextNode(rowArray[c]);
			url.append(url2);
			url.setAttribute("id", "par");
			url.style = "display:none;";
			td.appendChild(url);
		} else if(c == 6){
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

function updateRemoteAval(newValue, id){
	var data = {};
	data["videoID"] = id;
	data["status"] = newValue;
	
	var js = JSON.stringify(data);
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", updateRemoteStatusURL, true);
    xhr.send(js);
 	   
    xhr.onloadend = function () {
    	if (xhr.readyState == XMLHttpRequest.DONE) {
    		console.log ("XHR:" + xhr.responseText);
    		clearVideos();
    		getVideos();
    	} else {
    		console.log("error update remote status");
		}
    };
}

function clearVideos(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("videosTable");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}
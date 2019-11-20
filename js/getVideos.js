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
	    var tempArray = [];
	    tempArray.push(constantJson["name"]);
	    tempArray.push(constantJson["character"]);
	    tempArray.push(constantJson["sentence"]);
	    tempArray.push(constantJson["remotelyAccessible"]);
//	    tempArray.push("Trash");
	    insertRow(tempArray);
	}
	
	
}

function insertRow(rowArray) {
	var table = document.getElementById("videosTableBody"); //"videosTable");
	var tr = table.insertRow(table.rows.length);
	
	for(var c = 0; c < rowArray.length+1; c++){
		var td = document.createElement("td");
		td = tr.insertCell(c);
		if(c==3) {
			var check = document.createElement("input");
			check.setAttribute("type", "checkbox");
			if(rowArray[c] == true){
				check.setAttribute("value", "1");
			} else {
				check.setAttribute("value", "2");
			}
			
			td.appendChild(check);
		} else if(c == 4){
			var trash = document.createElement("input");
			trash.setAttribute("type", "button");
			trash.setAttribute("value", "Trash");
			td.appendChild(trash);
		} else {
			var element = document.createElement("P");
			element.innerHTML = rowArray[c];
			//element.setAttribute("value", tempArray[c]);
			
			td.appendChild(element);
		}
	}
}
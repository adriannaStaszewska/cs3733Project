function getRemotes() {
	var xhr = new XMLHttpRequest();
   xhr.open("GET", getRemotes, true);
   xhr.send();
	   
   console.log("sent remote URLs request");
	   
   xhr.onloadend = function () {
	   if (xhr.readyState == XMLHttpRequest.DONE) {
		   console.log ("XHR:" + xhr.responseText);
		   processRemoteList(xhr.responseText);
	   } else {
		   processVideoList("N/A");
	   }
   };
}

function processRemoteList(result) {
	console.log("res:" + result);
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    console.log(constantJson);
	    insertRemoteRow(constantJson["url"]);
	}
	
	
}

function insertRemoteRow(rowInput) {
	var table = document.getElementById("remoteTableBody");
	var tr = table.insertRow(table.rows.length);
	
	var td = document.createElement("td");
	td = tr.insertCell();
	var element = document.createElement("P");
	element.innerHTML = rowInput;
	
	td.appendChild(element);
	
}
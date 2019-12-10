//request list of remote sites
function getRemotes() {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", getRemotesURL, true);
	xhr.send();
 
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			processRemoteList(xhr.responseText);
		} else {
		}
	};
}

//process remote sites return
function processRemoteList(result) {
	var js = JSON.parse(result);
	
	for (var i = 0; i < js.list.length; i++) {
		var constantJson = js.list[i];
	    insertRemoteRow(constantJson["url"]);
	}
}

//insert remote entry into row
function insertRemoteRow(rowInput) {
	var table = document.getElementById("remoteTableBody");
	var tr = table.insertRow(table.rows.length);
	
	var td = document.createElement("td");
	td = tr.insertCell();
	var element = document.createElement("P");
	element.innerHTML = rowInput;
	
	td.appendChild(element);
	
	var td = document.createElement("td");
	td = tr.insertCell();
	var trash = document.createElement("input");
	trash.setAttribute("type", "button");
	trash.setAttribute("value", "Trash");
	trash.onclick = function (){
		var id = document.getElementById("remoteTable").rows[this.parentNode.parentNode.rowIndex].cells[0].innerText;
		removeRemote(id);
	}
	td.appendChild(trash);
	
}

//clear list of remote sites
function clearRemotes(){
	var tableHeaderRowCount = 1;
	var table = document.getElementById("remoteTable");
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
}
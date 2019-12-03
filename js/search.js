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
    	
    }
    

}


function searchLocal(sentence, character) {
    var data = {};
//	data["local"] = local;
//	data["remote"] = remote;
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
    		processSearch(xhr.responseText);
//    		processVideoList(xhr.responseText);
    	} else {
//    		processVideoList("N/A");
    		console.log("error");
		}
    };
}

function searchRemote(sentence, character) {
	
}

function processSearch(result) {
	console.log(result);
}
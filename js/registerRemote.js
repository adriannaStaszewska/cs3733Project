function handleModal(e) {
    document.getElementById("registerModal").style.visibility = 'visible';
    document.getElementById("registerModalContent").style.visibility = 'visible';
    document.getElementById("fullmodal").style.visibility = 'visible';
    document.getElementById("registerModal").style.zIndex = '1';
    document.getElementById("fullmodal").style.zIndex = '3';
}

function closeModal(e){
    document.getElementById("registerModal").style.visibility = 'hidden';
    document.getElementById("registerModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
    document.getElementById("registerModal").style.zIndex = '-1';
    document.getElementById("fullmodal").style.zIndex = '-1';
}

function handleRegisterRemoteSite(e) {
    console.log("Register remote site button triggered");
    document.getElementById("registerModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
    document.getElementById("registerModal").style.zIndex = '-1';
    document.getElementById("fullmodal").style.zIndex = '-1';
    
    createRemote(document.getElementById("urlField").value, document.getElementById("apiField").value);
    
    document.getElementById("urlField").value = "";
    document.getElementById("apiField").value = "";
}

function createRemote(url, api) {
	var data = {};
	data["url"] = url;
	data["api_key"] = api;
	
	var js = JSON.stringify(data);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", addRemoteURL, true);
	
	xhr.send(js);
	
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			if (xhr.status == 200) {
				clearRemotes();
				getRemotes();
			} else {
				console.log("actual:" + xhr.responseText)
				var js = JSON.parse(xhr.responseText);
				var err = js["response"];
				alert (err);
			}
		}
	};
}
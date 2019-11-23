function handleModal(e) {
    console.log("Modal Register remote site button triggered");
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
    createRemote(document.getElementById("urlField").value);
    document.getElementById("urlField").value = "";
}

function createRemote(url) {
	var data = {};
	data["url"] = url;
	
	var js = JSON.stringify(data);
	console.log(js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", addRemoteURL, true);
	
	xhr.send(js);
	
	 xhr.onloadend = function () {
		console.log(xhr);
		console.log(xhr.request);
		if (xhr.readyState == XMLHttpRequest.DONE) {
			 if (xhr.status == 200) {
		      console.log ("XHR:" + xhr.responseText);
		      clearRemotes();
		      getRemotes();
		 } else {
			 console.log("actual:" + xhr.responseText)
		  var js = JSON.parse(xhr.responseText);
		  var err = js["response"];
				  alert (err);
			 }
		} else {
	    }
	 };
}
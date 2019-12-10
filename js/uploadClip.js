//make upload modal visible
function handleModal(e) {
    document.getElementById("uploadModal").style.visibility = 'visible';
    document.getElementById("uploadModalContent").style.visibility = 'visible';
    document.getElementById("fullmodal").style.visibility = 'visible';
    document.getElementById("uploadModal").style.zIndex = '1';
    document.getElementById("fullmodal").style.zIndex = '3';
}

//make upload modal invisible
function closeModal(e){
    document.getElementById("uploadModal").style.visibility = 'hidden';
    document.getElementById("uploadModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
    document.getElementById("uploadModal").style.zIndex = '-1';
    document.getElementById("fullmodal").style.zIndex = '-1';
}

//upload video, make upload modal invisible
function handleUploadClip(e) {
    document.getElementById("uploadModal").style.visibility = 'hidden';
    document.getElementById("uploadModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
    document.getElementById("uploadModal").style.zIndex = '-1';
    document.getElementById("fullmodal").style.zIndex = '-1';
    uploadClip();
}

//send request to upload video
function uploadClip(){
	var data = {};
	data["videoName"] = document.getElementById("uploadName").value;
	data["character"] = document.getElementById("uploadCharacter").value;
	data["sentence"] = document.getElementById("uploadSentence").value;
	var segments = document.getElementById('uploadForm').base64Encoding.value.split(',');
	data["videoFile"] = segments[1];
	
	var js = JSON.stringify(data);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", uploadClipURL, true);
	
	xhr.send(js);
	
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			if (xhr.status == 200) {
				clearVideos();
				getVideos();
			} else {
				console.log("actual:" + xhr.responseText)
				var js = JSON.parse(xhr.responseText);
				var err = js["response"];
				alert (err);
			}
		}
	};
}
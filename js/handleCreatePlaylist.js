function handleModal(e) {
    console.log("Modal new playlist button triggered");
    document.getElementById("playlistModal").style.visibility = 'visible';
    document.getElementById("playlistModalContent").style.visibility = 'visible';
    document.getElementById("fullmodal").style.visibility = 'visible';
}

function closeModal(e){
    document.getElementById("playlistModal").style.visibility = 'hidden';
    document.getElementById("playlistModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
}

function handleUploadClip(e) {
    console.log("Handle new playlist button triggered");
    document.getElementById("playlistModal").style.visibility = 'hidden';
    document.getElementById("playlistModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
//    uploadClip();
}

//function uploadClip(){
//	var data = {};
//	data["name"] = document.getElementById("uploadName").value;
//	data["character"] = document.getElementById("uploadCharacter").value;
//	data["sentence"] = document.getElementById("uploadSentence").value;
//	var segments = document.getElementById('uploadForm').base64Encoding.value.split(',');
//	data["base64EncodedValue"] = segments[1];
//	
//	var js = JSON.stringify(data);
//	console.log(js);
//	var xhr = new XMLHttpRequest();
//	xhr.open("POST", uploadClipURL, true);
//	
//	xhr.send(js);
//	
//	 xhr.onloadend = function () {
//		    console.log(xhr);
//		    console.log(xhr.request);
//		    if (xhr.readyState == XMLHttpRequest.DONE) {
//		    	 if (xhr.status == 200) {
//			      console.log ("XHR:" + xhr.responseText);
//			      //processCreateResponse(xhr.responseText);
//		    	 } else {
//		    		 console.log("actual:" + xhr.responseText)
//					  var js = JSON.parse(xhr.responseText);
//					  var err = js["response"];
//					  alert (err);
//		    	 }
//		    } else {
//		      //processCreateResponse("N/A");
//		    }
//		  };
}
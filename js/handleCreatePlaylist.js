function handlePlaylistModal(e) {
    console.log("Modal new playlist button triggered");
    document.getElementById("playlistModal").style.visibility = 'visible';
    document.getElementById("playlistModalContent").style.visibility = 'visible';
    document.getElementById("fullmodal").style.visibility = 'visible';
    document.getElementById("playlistModal").style.zIndex = '1';
    document.getElementById("fullmodal").style.zIndex = '3';
}

function closeModal(e){
    document.getElementById("playlistModal").style.visibility = 'hidden';
    document.getElementById("playlistModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
    document.getElementById("playlistModal").style.zIndex = '-1';
    document.getElementById("fullmodal").style.zIndex = '-1';
}

function handleNewPlaylist(e) {
    console.log("Handle new playlist button triggered");
    document.getElementById("playlistModal").style.visibility = 'hidden';
    document.getElementById("playlistModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
    document.getElementById("playlistModal").style.zIndex = '-1';
    document.getElementById("fullmodal").style.zIndex = '-1';
    createPlaylist(document.getElementById("playlistName").value);
    document.getElementById("playlistName").value = "";
}

function createPlaylist(name) {
	var data = {};
	data["playlistName"] = name;
	
	var js = JSON.stringify(data);
	console.log(js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", createNewPlaylistURL, true);
	
	xhr.send(js);
	
	 xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			 if (xhr.status == 200) {
		      clearPlaylists();
		      getPlaylists();
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

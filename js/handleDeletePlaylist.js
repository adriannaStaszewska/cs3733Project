//send request to delete playlist
function handleDeletePlaylist (name) {
	var data = {};
	data["playlistName"] = name;
	
	var js = JSON.stringify(data);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", removePlaylistURL, true);
	
	xhr.send(js);
	
	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("XHR:" + xhr.responseText);
			if (xhr.status == 200) {
				document.getElementById("addVideoToPlaylistButton").style.visibility = 'hidden';
				document.getElementById("playButton").style.visibility = 'hidden';
				clearPlaylists();
				clearPlaylistVideos();
				getPlaylists();
			} else {
				console.log("actual:" + xhr.responseText)
				var js = JSON.parse(xhr.responseText);
				var err = js["response"];
				alert (err);
			 }
		}
	};
}
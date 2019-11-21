function handleDeletePlaylist (name) {
	console.log(name);
	var data = {};
	data["playlistName"] = name;
	
	var js = JSON.stringify(data);
	console.log(js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", removePlaylistURL, true);
	
	xhr.send(js);
	
	 xhr.onloadend = function () {
		console.log(xhr);
		console.log(xhr.request);
		if (xhr.readyState == XMLHttpRequest.DONE) {
			 if (xhr.status == 200) {
		      console.log ("XHR:" + xhr.responseText);
		      clearPlaylists();
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
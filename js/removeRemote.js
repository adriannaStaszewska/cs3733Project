//send request to remove remote site
function removeRemote(remote){
	var data = {};
	data["url"] = remote;

	var js = JSON.stringify(data);
	console.log(js);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", removeRemoteURL, true);

	xhr.send(js);

	xhr.onloadend = function () {
		if (xhr.readyState == XMLHttpRequest.DONE) {
			console.log ("200 XHR:" + xhr.responseText);
			if (xhr.status == 200) {
				var js = JSON.parse(xhr.responseText);
				if(js["statusCode"] == 200){
					clearRemotes();
					getRemotes();
				}
			} else {
				console.log("actual:" + xhr.responseText)
				var js = JSON.parse(xhr.responseText);
				var err = js["response"];
				alert (err);
			}
		}
	};
}
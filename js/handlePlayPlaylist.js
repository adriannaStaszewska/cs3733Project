var videosToPlay = [];

function handlePlayPlaylistModal(){
	document.getElementById("playPlaylistPlayerModal").style.visibility = 'visible';
    document.getElementById("playPlaylistModal").style.visibility = 'visible';
    document.getElementById("playPlaylistModalContent").style.visibility = 'visible';
    document.getElementById("playPlaylistPlayerModal").style.zIndex = '1';
    document.getElementById("playPlaylistModalContent").style.zIndex = '3';
    
    var tableTemp = document.getElementById("videosTablePBody");
	var rowsNotSelected = tableTemp.getElementsByTagName('tr');
//	var videosToPlay = []
	for (var row = 0; row < rowsNotSelected.length; row++) {
		videosToPlay.push(rowsNotSelected[row].cells[4].innerText);
	}
	console.log(videosToPlay);
	document.getElementById("player").src = videosToPlay.pop();
	
//	document.getElementById("player").addEventListener('ended',myHandler,false);
//    function myHandler(e) {
//        // What you want to do after the event
//    	if(videosToPlay.length > 0){
//    		console.log("here");
//    		console.log(videosToPlay);
//    		document.getElementById("player").src = videosToPlay.pop();
//    	} else {
//    		console.log("closing");
//    		closePlaylistModal();
//    	}
//    }
//	document.getElementById("player").add
//	var videoID = document.getElementById("addVideosTableP").rows[selected].cells[4].innerText;
    
}



function closePlaylistModal() {
    document.getElementById("playPlaylistPlayerModal").style.visibility = 'hidden';
    document.getElementById("playPlaylistModal").style.visibility = 'hidden';
    document.getElementById("playPlaylistModalContent").style.visibility = 'hidden';
    document.getElementById("playPlaylistPlayerModal").style.zIndex = '-1';
    document.getElementById("playPlaylistModalContent").style.zIndex = '-1';
}
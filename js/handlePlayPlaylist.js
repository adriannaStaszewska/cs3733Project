var videosToPlay = []; //global list of videos to currently play in order

//make video player visible, play playlist
function handlePlayPlaylistModal(){
	document.getElementById("playPlaylistPlayerModal").style.visibility = 'visible';
    document.getElementById("playPlaylistModal").style.visibility = 'visible';
    document.getElementById("playPlaylistModalContent").style.visibility = 'visible';
    document.getElementById("playPlaylistPlayerModal").style.zIndex = '1';
    document.getElementById("playPlaylistModalContent").style.zIndex = '3';
    
    var tableTemp = document.getElementById("videosTablePBody");
	var rowsNotSelected = tableTemp.getElementsByTagName('tr');

	if(rowsNotSelected.length <= 0){
		return;
	}
	
	for (var row = 0; row < rowsNotSelected.length; row++) {
		videosToPlay.push(rowsNotSelected[row].cells[4].innerText);
	}
	document.getElementById("player").src = videosToPlay.shift();
	
}

//make video player invisible
function closePlaylistModal() {
	document.getElementById("player").pause();
    document.getElementById("playPlaylistPlayerModal").style.visibility = 'hidden';
    document.getElementById("playPlaylistModal").style.visibility = 'hidden';
    document.getElementById("playPlaylistModalContent").style.visibility = 'hidden';
    document.getElementById("playPlaylistPlayerModal").style.zIndex = '-1';
    document.getElementById("playPlaylistModalContent").style.zIndex = '-1';
}
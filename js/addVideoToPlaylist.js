function addVideoToSelectedPlaylist(input) {
	console.log("adding video to playlist");
	
  document.getElementById("playlistAddModal").style.visibility = 'visible';
  document.getElementById("playlistAddModalContent").style.visibility = 'visible';
  document.getElementById("addModal").style.visibility = 'visible';
  document.getElementById("playlistAddModal").style.zIndex = '1';
  document.getElementById("addModal").style.zIndex = '3';
	
	var tableTemp = document.getElementById("playlistTableBody");
	var rowsNotSelected = tableTemp.getElementsByTagName('tr');
	var selected = -1;
    for (var row = 0; row < rowsNotSelected.length; row++) {
        if(rowsNotSelected[row].classList.contains("selected")){
        	selected = row;
        }
    }
    
    var playlistName = document.getElementById("playlistTableBody").rows[selected].cells[0].innerText;
    console.log(playlistName);
}

function addVideo(input) {
	console.log("Handle add video to playlist button triggered");
    document.getElementById("playlistAddModal").style.visibility = 'hidden';
    document.getElementById("playlistAddModalContent").style.visibility = 'hidden';
    document.getElementById("addModal").style.visibility = 'hidden';
    document.getElementById("playlistAddModal").style.zIndex = '-1';
    document.getElementById("addModal").style.zIndex = '-1';
}

function closeAddModal(e){
    document.getElementById("playlistAddModal").style.visibility = 'hidden';
    document.getElementById("playlistAddModalContent").style.visibility = 'hidden';
    document.getElementById("addModal").style.visibility = 'hidden';
    document.getElementById("playlistAddModal").style.zIndex = '-1';
    document.getElementById("addModal").style.zIndex = '-1';
}

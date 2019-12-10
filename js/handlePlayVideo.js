function handlePlayModal(row) {

    document.getElementById("videoPlayerModal").style.visibility = 'visible';
    document.getElementById("playerModal").style.visibility = 'visible';
    document.getElementById("videoModalContent").style.visibility = 'visible';
    document.getElementById("videoPlayerModal").style.zIndex = '1';
    document.getElementById("videoModalContent").style.zIndex = '3';

    document.getElementById("player").src = document.getElementById("videosTable").rows[row].getElementsByTagName("p")[3].innerHTML;
}

function closePlayModal() {
	document.getElementById("player").pause();
    document.getElementById("videoPlayerModal").style.visibility = 'hidden';
    document.getElementById("playerModal").style.visibility = 'hidden';
    document.getElementById("videoModalContent").style.visibility = 'hidden';
    document.getElementById("videoPlayerModal").style.zIndex = '-1';
    document.getElementById("videoModalContent").style.zIndex = '-1';
}
function handlePlayModal(row) {
    document.getElementById("videoPlayerModal").style.visibility = 'visible';
    document.getElementById("playerModal").style.visibility = 'visible';
    document.getElementById("videoModalContent").style.visibility = 'visible';

    console.log(document.getElementById("videosTable").rows[row].getElementsByTagName("p"));
    document.getElementById("player").src = document.getElementById("videosTable").rows[row].getElementsByTagName("p")[3].innerHTML;
}

function closePlayModal() {
    document.getElementById("videoPlayerModal").style.visibility = 'hidden';
    document.getElementById("playerModal").style.visibility = 'hidden';
    document.getElementById("videoModalContent").style.visibility = 'hidden';
}
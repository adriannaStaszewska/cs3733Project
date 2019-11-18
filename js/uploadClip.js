function handleModal(e) {
    console.log("Modal Upload clip button triggered");
    document.getElementById("uploadModal").style.visibility = 'visible';
    document.getElementById("uploadModalContent").style.visibility = 'visible';
    document.getElementById("fullmodal").style.visibility = 'visible';
}

function closeModal(e){
    document.getElementById("uploadModal").style.visibility = 'hidden';
    document.getElementById("uploadModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
}

function handleUploadClip(e) {
    console.log("Handle Upload clip button triggered");
    document.getElementById("uploadModal").style.visibility = 'hidden';
    document.getElementById("uploadModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
}
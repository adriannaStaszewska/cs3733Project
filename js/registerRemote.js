function handleModal(e) {
    console.log("Modal Register remote site button triggered");
    document.getElementById("registerModal").style.visibility = 'visible';
    document.getElementById("registerModalContent").style.visibility = 'visible';
    document.getElementById("fullmodal").style.visibility = 'visible';
}

function closeModal(e){
    document.getElementById("registerModal").style.visibility = 'hidden';
    document.getElementById("registerModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
}

function handleRegisterRemoteSite(e) {
    console.log("Register remote site button triggered");
    document.getElementById("registerModalContent").style.visibility = 'hidden';
    document.getElementById("fullmodal").style.visibility = 'hidden';
}
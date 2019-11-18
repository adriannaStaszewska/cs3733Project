function handleSearch(e) {
    var form = document.search;
    var searchTerms = form.searchBox.value;

    var local = form.localCheck.checked;
    var remote = form.remoteCheck.checked;

    console.log(searchTerms);
    console.log("Local: " + local);
    console.log("Remote: " + remote);
}
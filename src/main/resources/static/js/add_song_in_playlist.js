function add_song(playlistId, songId) {
    let token = $("meta[name='_csrf']").attr("content");
    let header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr) {
        xhr.setRequestHeader(header, token);
    });

    let data = {
        playlistId: playlistId,
        songId: songId
    };
    let icon = document.getElementById("plus-content " + songId);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/playlist/add-song",
        data: JSON.stringify(data),
        success:function(response) {
            //alert(response);
            icon.style.visibility = "hidden"
        },
        error:function () {
            alert("error")
        }
    });
}

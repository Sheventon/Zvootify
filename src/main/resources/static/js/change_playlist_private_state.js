function change_private(id) {
    let token = $("meta[name='_csrf']").attr("content");
    let header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr) {
        xhr.setRequestHeader(header, token);
    });

    let data = {
        id: id
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/change-playlist-private-state",
        data: JSON.stringify(data),
        success:function(response) {
            //alert("krasava")
            //alert("RESPONSE: " + response);
        },
        error:function () {
            alert("error")
        }
    });
}

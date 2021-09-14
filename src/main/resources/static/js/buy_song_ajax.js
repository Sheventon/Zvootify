function buy(id) {
    let token = $("input[name='_csrf']").val();
    let header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr) {
        xhr.setRequestHeader(header, token);
    });

    let data = {
        id: id
    };

    let icon = document.getElementById("buy-song " + id);
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/buy-song",
        data: JSON.stringify(data),
        success:function() {
            //alert(response);
            icon.style.visibility = "hidden";
        }
    });
}

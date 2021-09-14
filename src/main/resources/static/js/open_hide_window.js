function toogle_visibility(id) {
    var e = document.getElementById(id);
    var button = document.getElementById('timetable-button');
    if(e.style.display === 'block') {
        e.style.display = 'none';
        button.value = "Показать";
    }
    else {
        e.style.display = 'block';
        button.value = "Скрыть";
    }
}

function update_visibility(id) {
    var e = document.getElementById(id);
        e.style.display = 'flex';
}

function hide_update_visibility(id) {
    var e = document.getElementById(id);
        e.style.display = 'none';
}
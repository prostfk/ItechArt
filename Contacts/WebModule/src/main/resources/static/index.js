function sendJson(url, data, method) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    // xhr.setRequestHeader('Content-type','application/json');
    console.log(typeof data);
    xhr.send(data);
}

function createNav() {
    var str = '<meta name="viewport" content="width=device-width, initial-scale=1"><div class="topnav" id="myTopnav">' +
        '        <a href="/" class="active">CONTACTS</a>' +
        '        <a href="/contact/search">FIND CONTACT</a>' +
        '        <a href="/contact/addContact">ADD NEW CONTACT</a>' +
        '        <a href="/contacts/1">ALL CONTACTS</a>' +
        '        <a href="javascript:void(0);" class="icon" onclick="openMenu()">' +
        '            <i class="fa fa-bars"></i>' +
        '        </a>' +
        '    </div><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">';
    document.querySelector('body').insertAdjacentHTML('afterbegin',str);
};
function openMenu() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}
function sendJson(url, data, method) {
    var xhr = new XMLHttpRequest();
    xhr.open(method, url, true);
    // xhr.setRequestHeader('Content-type','application/json');
    console.log(typeof data);
    xhr.send(data);
}

function createNav() {
    var str = '<div class="topnav" id="myTopnav">' +
        '        <a href="#" class="active">CONTACTS</a>' +
        '        <a href="/contact/search">FIND CONTACT</a>' +
        '        <a href="/contact/addContact">ADD NEW CONTACT</a>' +
        '        <a href="/contacts/1">ALL CONTACTS</a>' +
        '        <a href="javascript:void(0);" class="icon" onclick="myFunction()">' +
        '            <i class="fa fa-bars"></i>' +
        '        </a>' +
        '    </div>';
    document.querySelector('body').insertAdjacentHTML('afterbegin',str);
};
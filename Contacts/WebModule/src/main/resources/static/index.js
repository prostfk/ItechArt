window.Promise = ES6Promise;
function createNav() {
    var headLinks = '<meta name="viewport" content="width=device-width, initial-scale=1"><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">';
    var str = '<div class="topnav" id="myTopnav">' +
        '        <a href="/" class="active">CONTACTS</a>' +
        '        <a href="/contact/search">FIND CONTACT</a>' +
        '        <a href="/contact/addContact">ADD NEW CONTACT</a>' +
        '        <a href="/contacts/1">ALL CONTACTS</a>' +
        '        <a href="javascript:void(0);" class="icon" onclick="openMenu()">' +
        '            <i class="fa fa-bars"></i>' +
        '        </a>';
    document.querySelector('head').insertAdjacentHTML('afterbegin',headLinks);
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


//EDIT CONTACT
function sendEditedContactInfo(id, formData) {
    fetch('/rest/contact/' + id + '/edit', {
        method: 'POST',
        body: formData
    }).then(function (response) {
        response.json().then(function (data) {
            if (data.error === undefined) {
                document.getElementById('contact-form').style.visibility = 'hidden';
                document.getElementById('message-span').style.color = 'green';
                document.getElementById('message-span').innerText = "Your contact was edited";
            }else{
                document.getElementById('message-span').style.color = 'red';
                document.getElementById('message-span').innerText = "Please, check your data!";
            }
        })
    });
}
//ADD ADDRESS
document.getElementById('sub-button').onclick = function sendForm() {
    var link = window.location.href.split("/");
    var id = link[link.length - 2];
    var formData = new FormData();
    formData.append('country', document.getElementById('country').value);
    formData.append('city', document.getElementById('city').value);
    formData.append('street', document.getElementById('street').value);
    formData.append('house', document.getElementById('house').value);
    formData.append('flat', document.getElementById('flat').value);
    formData.append('postIndex', document.getElementById('postIndex').value);
    fetch("/rest/contact/" + id + "/addAddress", {method: "post", body: formData}).then(function (response) {
        response.json().then(function (data) {
            console.log(data);
            if (data.id !== undefined) {
                document.getElementById('form-div').style.visibility = "hidden";
                document.getElementById('message-span').style.color = 'green';
                document.getElementById('message-span').innerText = "Your address added";
                document.getElementById('after-add').innerHTML = '<a class="butt" href="/contact/' + data.id + '">Check contact</a>';
            } else {
                document.getElementById('message-span').innerText = "Something went wrong";
                document.getElementById('message-span').style.color = 'red';
            }
        })
    })
};

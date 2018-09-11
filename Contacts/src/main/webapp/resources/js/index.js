function submitRegistration() {
    var name = document.getElementById('name').value;
    var surname = document.getElementById('surname').value;
    return name.length > 2 && surname.length > 3;
}


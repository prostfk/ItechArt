


function launch_toast() {
    let name = document.getElementById("fname").value;
    let lastname = document.getElementById("lname").value;
    if (name===''|| name===' '|| lastname===''||lastname===' '){
        let x = document.getElementById("toast");
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 5000);
    }else{
        let x = document.getElementById("toast");
        x.className = "show";
        x.innerHTML="<div id=\"img\"><img src=\"../icons/succes.ico\" width=\"25\" height=\"25\"/></div>\n" +
            "    <div id=\"desc\">Success</div>";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 5000);
    }

}
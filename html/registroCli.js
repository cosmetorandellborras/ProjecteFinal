function enviar(){
    var http;
    http = new XMLHttpRequest;
    let passenc = encPass(document.getElementById("contraseña").value);

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            var stat = parseInt(http.responseText);
            if (stat == 0){
                alert("Se ha registrado correctamente");
            }
            else if (stat == -1){
                alert("Ya existe un cliente con el mismo DNI");
            }
        }
    }

    http.open("POST","http://localhost:7070/ProjecteFinal/RegistroCli",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("nombre="+document.getElementById("nombre").value+"&&apellido="+document.getElementById("apellido").value+"&&telefono="+document.getElementById("telefono").value+"&&correo="+document.getElementById("correo").value+"&&contraseña="+passenc+"&&dni="+document.getElementById("dni").value);
}
function encPass(pass){
    let encrypted = CryptoJS.SHA256(pass);
    return encrypted;
}
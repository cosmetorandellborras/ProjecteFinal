function enviar(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            console.log(http.responseText);
            var stat = parseInt(http.responseText);
            if (stat == 0){
                alert("Se ha registrado correctamente");
            }
            else if (stat == -1){
                alert("Ya existe un trabajador con el mismo DNI");
            }
        }
    }

    http.open("POST","http://localhost:7070/ProjecteFinal/RegistroTrab",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("nombre="+document.getElementById("nombre").value+"&&apellido="+document.getElementById("apellido").value+"&&telefono="+document.getElementById("telefono").value+"&&correo="+document.getElementById("correo").value+"&&contraseña="+document.getElementById("contraseña").value+"&&dni="+document.getElementById("dni").value+"&&edad="+document.getElementById("edad").value);
}
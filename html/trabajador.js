function actualitzar(){
    listaTipoTrabajo();
    listaZona();
    listaDisponibilidad();
    mostrarDatosTrabajador();
    mostrarServicios();
}
function listaTipoTrabajo(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            document.getElementById("sel_trabajo").innerHTML = http.responseText;
        }
    }

    http.open("GET","http://localhost:7070/ProjecteFinal/GetSelTrabajoPublicar",true);
    http.send();
}
function listaZona(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            document.getElementById("sel_zona").innerHTML = http.responseText;
        }
    }

    http.open("GET","http://localhost:7070/ProjecteFinal/GetSelZonaPublicar",true);
    http.send();
}
function listaDisponibilidad(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            document.getElementById("sel_dispo").innerHTML = http.responseText;
        }
    }

    http.open("GET","http://localhost:7070/ProjecteFinal/GetSelDispoPublicar",true);
    http.send();
}
function publicar(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            var num = parseInt(http.responseText);
            if (num == -1){
                alert("Anuncio duplicado");
            }
            else if (num == 0) {
                alert("Anuncio publicado");
            }
            else if (num == 1){
                alert("Hubo algún problema al publicar el anuncio");
            }
        }
    }

    http.open("POST","http://localhost:7070/ProjecteFinal/InsertarAnuncio",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("trabajo="+document.getElementById("trabajo").value+"&&zona="+document.getElementById("zona").value+"&&dispo="+document.getElementById("disponibilidad").value+"&&precioHora="+document.getElementById("id_precio").value+"&&dni_trabajador="+sessionStorage.getItem("identificador"));
}
function mostrarDatosTrabajador(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            var obj = JSON.parse(http.responseText);
            document.getElementById("id_nombre").value = obj.nombre;
            document.getElementById("id_apellido").value = obj.apellido;
            document.getElementById("id_telefono").value = obj.telefono;
            document.getElementById("id_correo").value = obj.correo;
            document.getElementById("id_edad").value = obj.edad;
        }
    }

    http.open("GET","http://localhost:7070/ProjecteFinal/GetDatosTrab?dni="+sessionStorage.getItem("identificador"),true);
    http.send();
}
function actualizarDatos(){
    var http;
    http = new XMLHttpRequest;
    let passenc = encPass(document.getElementById("id_contraseña").value);

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            var num = parseInt(http.responseText);
            if (num == 1){
                alert("Se han actualizado los datos");
            }
            else {
                alert("Hubo algún problema");
            }
        }
    }

    http.open("POST","http://localhost:7070/ProjecteFinal/ActualizarTrab",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("nombre="+document.getElementById("id_nombre").value+"&&apellido="+document.getElementById("id_apellido").value+"&&telefono="+document.getElementById("id_telefono").value+"&&correo="+document.getElementById("id_correo").value+"&&contrasena="+passenc+"&&dni="+sessionStorage.getItem("identificador")+"&&edad="+document.getElementById("id_edad").value);
}
function encPass(pass){
    let encrypted = CryptoJS.SHA256(pass);
    return encrypted;
}
function mostrarServicios(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById("tabla_trabajos").innerHTML = http.responseText;
        }
    }

    http.open("POST", "http://localhost:7070/ProjecteFinal/MostrarServicioTrab", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("id="+sessionStorage.getItem("identificador"));
}

function actualizarServ(id_contratacion, estado){
    var http = new XMLHttpRequest;

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            var num = parseInt(http.responseText);
            if (num == 1){
                alert("Trabajo aceptado");
            }
            else if (num == -1) {
                alert("Trabajo rechazado");
            }
            actualitzar();
        }
    }

    http.open("POST", "http://localhost:7070/ProjecteFinal/ActualizarServ", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("id="+id_contratacion+"&&estado="+estado);
}
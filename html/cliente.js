function actualitzar(){
    listaTipoTrabajo();
    listaZona();
    listaDisponibilidad();
    mostrarDatosCliente();
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

    http.open("GET","http://localhost:7070/ProjecteFinal/GetSelTrabajo",true);
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

    http.open("GET","http://localhost:7070/ProjecteFinal/GetSelZona",true);
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

    http.open("GET","http://localhost:7070/ProjecteFinal/GetSelDispo",true);
    http.send();
}
function filtrar(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            document.getElementById("tabla_trabajos").innerHTML = http.responseText;
        }
    }

    http.open("POST","http://localhost:7070/ProjecteFinal/FiltrarAnuncios",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("trabajo="+document.getElementById("trabajo").value+"&&zona="+document.getElementById("zona").value+"&&dispo="+document.getElementById("disponibilidad").value);
}
function solicitar(num){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            var num = parseInt(http.responseText);
            if (num == 1){
                alert("Se ha procesado su solicitud correctamente");
            }
            else {
                alert("Hubo algún problema");
            }
        }
    }

    var d1 = new Date(prompt('Introduce la fecha de inicio (yyyy-mm-dd)'));
    var d2 = new Date(prompt('Introduce la fecha de fin (yyyy-mm-dd)'));
    var h1 = prompt('Introduce la hora de inicio (00-24): ');
    var h2 = prompt('Introduce la hora de fin (00-24): ');
    var dir = prompt('Introduce la dirección del domicilio: ');
    var timeDiff = Math.abs(d2.getTime() - d1.getTime());
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24))+1;
    var horas = (h2-h1)*diffDays;

    http.open("POST","http://localhost:7070/ProjecteFinal/ContratarServ",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("dnicliente="+sessionStorage.getItem("identificador")+"&&anuncio="+num+"&&direccion="+dir+"&&fechainicio="+convertir(d1)+"&&fechafin="+convertir(d2)+"&&horainicio="+h1+"&&horafin="+h2);

}
function convertir(str){
    var fecha = new Date(str);
    mes = ("0" + (fecha.getMonth()+1)).slice(-2);
    dia = ("0" + fecha.getDate()).slice(-2);
    return [dia,mes,fecha.getFullYear()].join("-");
}
function mostrarDatosCliente(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            var obj = JSON.parse(http.responseText);
            document.getElementById("id_nombre").value = obj.nombre;
            document.getElementById("id_apellido").value = obj.apellido;
            document.getElementById("id_telefono").value = obj.telefono;
            document.getElementById("id_correo").value = obj.correo;
        }
    }

    http.open("GET","http://localhost:7070/ProjecteFinal/GetDatosCli?dni="+sessionStorage.getItem("identificador"),true);
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

    http.open("POST","http://localhost:7070/ProjecteFinal/ActualizarCli",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("nombre="+document.getElementById("id_nombre").value+"&&apellido="+document.getElementById("id_apellido").value+"&&telefono="+document.getElementById("id_telefono").value+"&&correo="+document.getElementById("id_correo").value+"&&contrasena="+passenc+"&&dni="+sessionStorage.getItem("identificador"));
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
            document.getElementById("tabla_trabajos_solicitados").innerHTML = http.responseText;
        }
    }

    http.open("POST", "http://localhost:7070/ProjecteFinal/MostrarServicioCli", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("id="+sessionStorage.getItem("identificador"));
}
function valorarServ(id){
    var valoracion;
    let correcto = false;
    while (!correcto) {
        valoracion = Number(prompt("¿Cómo valorarías este servicio de 0 a 5? (sin decimales)"));
        if(Number.isInteger(valoracion) && valoracion >= 0 && valoracion <= 5){
            correcto = true;
        } else{
            alert("Debe ser un número entero entre 0 y 5");
        }
    }
    var comentario = prompt("Añade un comentario (opcional)");
    var http = new XMLHttpRequest;

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            var num = parseInt(http.responseText);
            if (num == 1){
                alert("Se ha insertado la valoración correctamente");
            }
            else {
                alert("Hubo algún problema");
            }
            actualitzar();
        }
    }

    http.open("POST", "http://localhost:7070/ProjecteFinal/ValorarServ", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("id="+id+"&&valoracion="+valoracion+"&&comentario="+comentario);
}

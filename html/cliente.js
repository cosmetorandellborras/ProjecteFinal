function actualitzar(){
    listaTipoTrabajo();
    listaZona();
    listaDisponibilidad();
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

function mostrarServicios(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById().textContent = http.responseText;
        }
    }

    http.open("POST", "http://localhost:7070/ProjecteFinal/MostrarServicioCli", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("id="+localStorage.getItem("identificador"));
}

function valorar(){
    var valoracion = parseInt(prompt("¿Cómo valorarías este servicio sobre 5?"));
    var comentario = prompt("Añade un comentario (opcional)");
}
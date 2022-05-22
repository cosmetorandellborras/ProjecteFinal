function actualitzar(){
    listaTipoTrabajo();
    listaZona();
    listaDisponibilidad();
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
function publicar(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange == function (){
        if (http.readyState == 4 && http.status == 200){
            alert("Anuncio publicado con éxito");
        }
    }

    http.open("POST","http://localhost:7070/ProjecteFinal/InsertarAnuncio",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("trabajo="+document.getElementById("trabajo").value+"&&zona="+document.getElementById("zona").value+"&&dispo="+document.getElementById("disponibilidad").value+"&&precioHora="+document.getElementById("id_precio").value+"&&trabajador="+document.getElementById("trabajador").value);
} 

function mostrarServicios(){
    var http;
    http = new XMLHttpRequest;

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            document.getElementById().textContent = http.responseText;
        }
    }

    http.open("POST", "http://localhost:7070/ProjecteFinal/MostrarServicioTrab", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("id="+localStorage.getItem("identificador"));
}

function actualitzarServ(id1, id2, id3){
    var http = new XMLHttpRequest;

    http.onreadystatechange = function(){
        if(http.readyState == 4 && http.status == 200){
            
        }
    }

    http.open("POST", "http://localhost:7070/ProjecteFinal/ActualizarServ", true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("id1="+id1+"&&id2="+id2+"&&id3="+id3);
}
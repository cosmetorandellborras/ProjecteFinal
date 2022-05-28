function enviarCli(){
    var http;
    http = new XMLHttpRequest;
    let passenc = encPass(document.getElementById("passw_cliente").value);
    var dni_cliente = document.getElementById("id_cliente").value;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            var num = parseInt(http.responseText);
            if (num == -1){
                alert("No existe ningun cliente con el DNI introducido");
            }
            else if(num == 0){
                alert("El password introducido es incorrecto");
            }
            else if(num == 1){
                alert("Login correcto");
                sessionStorage.setItem("identificador",dni_cliente);
                window.location.href = "/Users/cosmetorandell/Desktop/Activitats/Programaciò/2 evaluacio/ProjecteFinal/html/cliente.html";
            }
        }
    }
    http.open("POST","http://localhost:7070/ProjecteFinal/LoginCli",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("dni="+document.getElementById("id_cliente").value+"&&pass="+passenc);
}
function enviarTrab(){
    var http;
    http = new XMLHttpRequest;
    let passenc = encPass(document.getElementById("passw_colaborador").value);
    var dni_trabajador = document.getElementById("id_colaborador").value;

    http.onreadystatechange = function (){
        if (http.readyState == 4 && http.status == 200){
            var num = parseInt(http.responseText);
            if (num == -1){
                alert("No existe ningun cliente con el DNI introducido");
            }
            else if(num == 0){
                alert("El password introducido es incorrecto");
            }
            else if(num == 1){
                alert("Login correcto");
                sessionStorage.setItem("identificador",dni_trabajador);
                window.location.href = "/Users/cosmetorandell/Desktop/Activitats/Programaciò/2 evaluacio/ProjecteFinal/html/trabajador.html";
            }
        }
    }
    http.open("POST","http://localhost:7070/ProjecteFinal/LoginTrab",true);
    http.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    http.send("dni="+document.getElementById("id_colaborador").value+"&&pass="+passenc);
}
function encPass(pass){
    let encrypted = CryptoJS.SHA256(pass);
    return encrypted;
}
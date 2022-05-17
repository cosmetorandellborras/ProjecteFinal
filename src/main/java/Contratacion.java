import java.time.LocalDateTime;

public class Contratacion {
	//Atributos
	private String dniCliente;
	private Anuncio anuncio;
	private String direccion;
	private LocalDateTime fechaInicio;
	private String intervaloHoras;
	private LocalDateTime fechaContrato;
	private String comentario;
	private Estado estado;
	private float precio;
	//Getters y setters
	public String getDniCliente() {
		return dniCliente;
	}
	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getIntervaloHoras() {
		return intervaloHoras;
	}
	public void setIntervaloHoras(String intervaloHoras) {
		this.intervaloHoras = intervaloHoras;
	}
	public LocalDateTime getFechaContrato() {
		return fechaContrato;
	}
	public void setFechaContrato(LocalDateTime fechaContrato) {
		this.fechaContrato = fechaContrato;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	//Constructor
	public Contratacion(String dniCliente, Anuncio anuncio, String direccion, LocalDateTime fechaInicio,
			String intervaloHoras, LocalDateTime fechaContrato, String comentario, Estado estado, float precio) {
		this.dniCliente = dniCliente;
		this.anuncio = anuncio;
		this.direccion = direccion;
		this.fechaInicio = fechaInicio;
		this.intervaloHoras = intervaloHoras;
		this.fechaContrato = fechaContrato;
		this.comentario = comentario;
		this.estado = estado;
		this.precio = precio;
	}
	
	
	
}

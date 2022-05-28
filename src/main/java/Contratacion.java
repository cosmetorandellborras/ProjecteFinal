import java.time.LocalDate;

public class Contratacion {
	//Atributos
	private String dniCliente;
	private Anuncio anuncio;
	private String direccion;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private String intervaloHoras;
	private LocalDate fechaContrato;
	private String comentario;
	private float valoracion;
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
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getIntervaloHoras() {
		return intervaloHoras;
	}
	public void setIntervaloHoras(String intervaloHoras) {
		this.intervaloHoras = intervaloHoras;
	}
	public LocalDate getFechaContrato() {
		return fechaContrato;
	}
	public void setFechaContrato(LocalDate fechaContrato) {
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
	public void setValoracion(float valoracion) {
		this.valoracion = valoracion;
	}
	public float getValoracion() {
		return valoracion;
	}
	//Constructor
	public Contratacion(String dniCliente, Anuncio anuncio, String direccion, LocalDate fechaInicio, LocalDate fechaFin,
			String intervaloHoras, LocalDate fechaContrato, Estado estado, float precio) {
		this.setDniCliente(dniCliente);
		this.setAnuncio(anuncio);
		this.setDireccion(direccion);
		this.setFechaInicio(fechaInicio);
		this.setFechaFin(fechaFin);
		this.setIntervaloHoras(intervaloHoras);
		this.setFechaContrato(fechaContrato);
		this.setValoracion(0);
		this.setComentario(null);
		this.setEstado(estado);
		this.setPrecio(precio);
	}
	//Métodos
	public int inContrato() {
		int num = DataBase.insertarContratacion(this);
		return num;
	}
	
	
	
}

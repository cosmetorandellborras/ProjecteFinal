
public class Anuncio {
	private Integer id;
	private String dniTrabajador;
	private Zona zona;
	private String trabajo;
	private String disponibilidad;
	private float precioHora;
	
	//Getters y setters
	public Integer getId() {
		return id;
	}
	public void setId() {
		int num = DataBase.buscarUltimoIdAnuncio();
		this.id = num+1;
	}
	public String getDniTrabajador() {
		return dniTrabajador;
	}
	public void setDniTrabajador(String dniTrabajador) {
		this.dniTrabajador = dniTrabajador;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public String getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}
	public String getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public float getPrecioHora() {
		return precioHora;
	}
	public void setPrecioHora(float precioHora) {
		this.precioHora = precioHora;
	}
	//Constructores
	public Anuncio(String dniTrabajador, Zona zona, String trabajo, String disponibilidad,
			float precioHora) {
		this.setId();
		this.setDniTrabajador(dniTrabajador);
		this.setZona(zona);
		this.setTrabajo(trabajo);
		this.setDisponibilidad(disponibilidad);
		this.setPrecioHora(precioHora);
	}
	
	
}

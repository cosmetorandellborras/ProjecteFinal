
public class Anuncio {
	private Integer id;
	private String dniTrabajador;
	private Zona zona;
	private Trabajo trabajo;
	private Disponibilidad disponibilidad;
	private float precioHora;
	
	//Getters y setters
	public Integer getId() {
		return id;
	}
	public void setId() {
		int num = DataBase.buscarUltimoIdAnuncio();
		this.id = num+1;
	}
	public void setIdRecuperarAnuncio(Integer id) {
		this.id = id;
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
	public Trabajo getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}
	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public float getPrecioHora() {
		return precioHora;
	}
	public void setPrecioHora(float precioHora) {
		this.precioHora = precioHora;
	}
	//Constructores
	public Anuncio(String dniTrabajador, Zona zona, Trabajo trabajo, Disponibilidad disponibilidad,
			float precioHora) {
		this.setId();
		this.setDniTrabajador(dniTrabajador);
		this.setZona(zona);
		this.setTrabajo(trabajo);
		this.setDisponibilidad(disponibilidad);
		this.setPrecioHora(precioHora);
	}
	public Anuncio(Integer id) {
		this.id = id;
	}
	//Metodo
	public int publicarAnuncio(String trabajodb,String dispodb) {
		int num = -1;
		boolean trobat = DataBase.comprobarAnuncioDuplicado(this,trabajodb,dispodb);
		if (!trobat) {
			num = 0;
			DataBase.insertarAnuncio(this,trabajodb,dispodb);
		}
		return num;
	}
	
}

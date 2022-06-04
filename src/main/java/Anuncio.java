/**
 * Clase Anuncio
 * @author cosmetorandell
 *
 */
public class Anuncio {
	/**
	 * Atributos
	 */
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
	/**
	 * setId
	 * Busca en la base de datos el ultimo id de un anuncio, incrementa su valor en 1 y le asigna el nuevo id al objeto anuncio
	 */
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
	/**
	 * Constructor completo
	 * @param dniTrabajador
	 * @param zona
	 * @param trabajo
	 * @param disponibilidad
	 * @param precioHora
	 */
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
	/**
	 * Método publicarAnuncio
	 * Llama al método comprobarAnuncioDuplicado, si no lo encuentra, llama al método insertarAnuncio e inserta el nuevo anuncio en la base de datos
	 * @return num retorna un numero que identificará si la operación se ha completado satisfactoriamente o no
	 */
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

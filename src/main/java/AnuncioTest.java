import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnuncioTest {
	/**
	 * Test publicar Anuncio
	 * retorna -1 si ya existe el anuncio
	 * retorna 0 si se ha registrado correctamente
	 */
	@Test
	void testPublicarAnuncio() {
		Anuncio nuevo = new Anuncio(1);
		nuevo.setDniTrabajador("43191357w");
		nuevo.setZona(Zona.sur);
		nuevo.setTrabajo(Trabajo.repaso);
		nuevo.setDisponibilidad(Disponibilidad.domingomañana);
		nuevo.setPrecioHora(10);
		int num = nuevo.publicarAnuncio("canguro", "domingomañana");
		assertEquals(0,num);
	}

}

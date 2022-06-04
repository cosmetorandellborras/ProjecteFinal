import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrabajadorTest {
	/**
	 * Test registrar trabajador
	 * retorna -1 si existe un trabajador con el mismo dni
	 * retorna 0 si el registro es correcto
	 */
	@Test
	void testRegistrarUsuario() {
		Trabajador nuevo = new Trabajador("pepe","garcia",654456665,"pepe@gmail.com","1234","43337766r",34);
		int num = nuevo.registrarUsuario();
		assertEquals(0,num);
	}

}

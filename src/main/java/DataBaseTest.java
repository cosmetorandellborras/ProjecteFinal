import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataBaseTest {
	/**
	 * Test comprobarLoginCliente
	 * retorna -1 si no ha encontrado al cliente
	 * retorna 0 si ha encontrado el cliente pero el password introducido no coincide
	 * retorna 1 si el cliente y el password coinciden
	 */
	@Test
	void testComprobarLoginCliente() {
		int num = DataBase.comprobarLoginCliente("43191357w", "9c7a4311116e35c4cf62de65fb15bf3415a1965bd50e5dbc72b5abc7b93bfa33");
		assertEquals(1,num);
	}

}

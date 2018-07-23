package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import dominio.Libro;
import testdatabuilder.LibroTestDataBuilder;

public class LibroTest {

	private static final int ANIO = 2010;
	private static final String ISBN = "1234";
	private static final String ISBNPALINDROMO = "12321";
	private static final String NOMBRE_LIBRO = "Cien años de soledad";

	@Test
	public void crearLibroTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
				conTitulo(NOMBRE_LIBRO).
				conIsbn(ISBN).
				conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();

		// assert
		assertEquals(NOMBRE_LIBRO, libro.getTitulo());
		assertEquals(ISBN, libro.getIsbn());
		assertEquals(ANIO, libro.getAnio());
	}

	@Test
	public void esPalindromo() {

		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
				conTitulo(NOMBRE_LIBRO).
				conIsbn(ISBNPALINDROMO).
				conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();

		// assert
		assertTrue(libro.esPalindromo());
	}

	@Test
	public void noEsPalindromo() {

		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
				conTitulo(NOMBRE_LIBRO).
				conIsbn(ISBN).
				conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();

		// assert
		assertFalse(libro.esPalindromo());
	}

}

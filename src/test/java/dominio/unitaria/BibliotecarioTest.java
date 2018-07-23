package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import dominio.Prestamo;
import dominio.utilitarios.Mensajes;
import org.junit.Assert;
import org.junit.Test;

import dominio.Bibliotecario;
import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import testdatabuilder.LibroTestDataBuilder;
import testdatabuilder.PrestamoTestDataBuilder;

public class BibliotecarioTest {

    private static final int ANIO = 2010;
    private static final String ISBN = "1234";
    private static final String ISBNPALINDROMO = "12321";
    private static final String NOMBRE_LIBRO = "Cien años de soledad";
    private static final String NOMBRE_USUARIO = "Jose Alejandro Berrio Marin";

	@Test
	public void esPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertTrue(esPrestado);
	}
	
	@Test
	public void libroNoPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);
		
		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertFalse(esPrestado);
	}

	@Test
	public void prestarLibroTest() {

		// arrange
        LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();

        Libro libro = libroTestDataBuilder.build();

        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
		RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);

        when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);
        when(repositorioLibro.obtenerPorIsbn(libro.getIsbn())).thenReturn(libro);

		Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);

		// act
        try{
            String respuesta = bibliotecario.prestar(libro.getIsbn(), NOMBRE_USUARIO);
        } catch (Exception e) {
            // assert
            assertEquals(e.getMessage(), Mensajes.EXITO.get());
        }
	}

    @Test
    public void prestarLibroYaPrestadoTest() {

        // arrange
        LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();

        Libro libro = libroTestDataBuilder.build();

        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);

        when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);
        when(repositorioLibro.obtenerPorIsbn(libro.getIsbn())).thenReturn(libro);

        Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);

        // act
        try{
            String respuesta = bibliotecario.prestar(libro.getIsbn(), NOMBRE_USUARIO);
        } catch (Exception e) {
            // assert
            assertEquals(e.getMessage(), Mensajes.YAPRESTADO.get());
        }
    }

    @Test
    public void prestarLibroPalindromo() {

        // arrange
        LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().
                conTitulo(NOMBRE_LIBRO).
                conIsbn(ISBNPALINDROMO).
                conAnio(ANIO);

        Libro libro = libroTestDataBuilder.build();

        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);

        when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);
        when(repositorioLibro.obtenerPorIsbn(libro.getIsbn())).thenReturn(libro);

        Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);

        // act
        try{
            String respuesta = bibliotecario.prestar(libro.getIsbn(), NOMBRE_USUARIO);
        } catch (Exception e) {
            // assert
            assertEquals(e.getMessage(), Mensajes.ERRORPALINDROMO.get());
        }
    }

    @Test
    public void noEncuentraLibroBD() {

        // arrange
        LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();

        Libro libro = libroTestDataBuilder.build();

        RepositorioPrestamo repositorioPrestamo = mock(RepositorioPrestamo.class);
        RepositorioLibro repositorioLibro = mock(RepositorioLibro.class);

        when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);
        when(repositorioLibro.obtenerPorIsbn(libro.getIsbn())).thenReturn(null);

        Bibliotecario bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);

        // act
        try{
            String respuesta = bibliotecario.prestar(libro.getIsbn(), NOMBRE_USUARIO);
        } catch (Exception e) {
            // assert
            assertEquals(e.getMessage(), Mensajes.ERROR.get());
        }
    }
}

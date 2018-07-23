package dominio;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import dominio.utilitarios.Mensajes;
import sun.rmi.runtime.Log;

import java.util.Date;

public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro ya fue prestado, y no se puede prestar mas de una vez.";

	private RepositorioLibro repositorioLibro;
	private RepositorioPrestamo repositorioPrestamo;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;

	}

	public String prestar(String isbn, String nombreUsuario) {

		Libro libroPrestado = repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
		if(libroPrestado==null) {
			Libro libro = repositorioLibro.obtenerPorIsbn(isbn);
			if(libro != null ) {
				if (!libro.esPalindromo()) {
					Prestamo newPrestamo = new Prestamo(new Date(), libro, nombreUsuario);
					repositorioPrestamo.agregar(newPrestamo);
				} else {
					throw new PrestamoException(Mensajes.ERRORPALINDROMO.get());
				}
			} else {
				return Mensajes.ERROR.get();
			}

		} else {
			throw new PrestamoException(Mensajes.YAPRESTADO.get());
		}

		return Mensajes.EXITO.get();
	}

	public boolean esPrestado(String isbn) {

		Libro libroPrestado = repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
		return libroPrestado==null?false:true;
	}

}

package dominio;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import dominio.utilitarios.Mensajes;
import sun.rmi.runtime.Log;

import java.util.Date;

public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";

	private RepositorioLibro repositorioLibro;
	private RepositorioPrestamo repositorioPrestamo;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;

	}

	public String prestar(String isbn) {
		Libro libro = repositorioLibro.obtenerPorIsbn(isbn);

		Prestamo prestamo = new Prestamo(new Date(), libro, "alejo");

		repositorioPrestamo.agregar(prestamo);

		return Mensajes.EXITO.get();

	}

	public boolean esPrestado(String isbn) {
		return true;
	}

}

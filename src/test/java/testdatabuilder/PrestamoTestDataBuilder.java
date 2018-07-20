package testdatabuilder;

import dominio.Libro;
import dominio.Prestamo;

import java.util.Date;

public class PrestamoTestDataBuilder {

	private static final Date fecha = new Date(2018,01,01);
	private static final Date fechaMax = new Date(2018,01,17);
	private static final String NOMBRE_USUARIO = "Alejandro Berrio";
	private static final Libro libroEjemplo = new Libro("1234", "Cien Años de Soledad", 2018);

	private Date fechaSolicitud;
	private Libro libro;
	private Date fechaEntregaMaxima;
	private String nombreUsuario;

	public PrestamoTestDataBuilder() {
		this.fechaSolicitud = this.fecha;
		this.libro = this.libroEjemplo;
		this.fechaEntregaMaxima = this.fechaMax;
		this.nombreUsuario = this.NOMBRE_USUARIO;
	}

	public PrestamoTestDataBuilder conLibro(Libro libro) {
		this.fechaSolicitud = this.fecha;
		this.libro = libro;
		this.fechaEntregaMaxima = this.fechaMax;
		this.nombreUsuario = this.NOMBRE_USUARIO;
		return this;
	}

	public Prestamo build() {
		return new Prestamo(this.fecha, this.libroEjemplo, this.fechaMax, this.NOMBRE_USUARIO);
	}
}

package dominio;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class Prestamo {

	private Date fechaSolicitud;
	private Libro libro;
	private Date fechaEntregaMaxima;
	private String nombreUsuario;

	public Prestamo(Libro libro) {
		this.fechaSolicitud = new Date();
		this.libro = libro;
	}

	public Prestamo(Date fechaSolicitud, Libro libro, String nombreUsuario) {
		this.fechaSolicitud = fechaSolicitud;
		this.libro = libro;
		this.fechaEntregaMaxima = calculaFechaMaxima(fechaSolicitud, libro.getIsbn());
		this.nombreUsuario = nombreUsuario;
	}

	public Prestamo(Date fechaSolicitud, Libro libro, Date fechaEntregaMaxima, String nombreUsuario) {
		this.fechaSolicitud = fechaSolicitud;
		this.libro = libro;
		this.fechaEntregaMaxima = fechaEntregaMaxima;
		this.nombreUsuario = nombreUsuario;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public Libro getLibro() {
		return libro;
	}

	public Date getFechaEntregaMaxima() {
		return fechaEntregaMaxima;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	private Date calculaFechaMaxima(Date fechaSolicitud, String isbn) {
		Date fechaMax = null;
		if(sumarIsbn(isbn)>30) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaSolicitud);
			calendar.add(Calendar.DATE, 16);
			int numeroDia=calendar.get(Calendar.DAY_OF_WEEK);
			if(numeroDia-1==0) {
				calendar.add(Calendar.DAY_OF_YEAR, 1);
			}
			fechaMax = calendar.getTime();
		}
		return fechaMax;
	}

	private int sumarIsbn(String isbn) {
		int retorno=0;
		int length = isbn.length();
		for (int i = length - 1; i >= 0; i-- ) {
			if(isbn.charAt(i) >= '0' && isbn.charAt(i) <= '9') {
				retorno = retorno + Character.getNumericValue(isbn.charAt(i));
			}
		}
		return retorno;
	}

}

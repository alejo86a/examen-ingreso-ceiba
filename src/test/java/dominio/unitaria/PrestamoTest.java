package dominio.unitaria;

import dominio.Libro;
import dominio.Prestamo;
import org.junit.Test;
import testdatabuilder.LibroTestDataBuilder;
import testdatabuilder.PrestamoTestDataBuilder;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class PrestamoTest {

    private static final Date fechaMaxMayorTreinta = new Date(2018,01,17);
    private static final String NOMBRE_USUARIO = "Alejandro Berrio";
    private static final Libro libroEjemplo = new Libro("1234", "Cien Anios de Soledad", 2018);
    private static final Libro libroIsbnMayorTreinta = new Libro("A874B69Q", "Cien Anios de Soledad", 2018);

    @Test
    public void crearPrestamoTest() {

        // arrange
        Date fecha = new Date(2018,01,01);
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder();

        // act
        Prestamo prestamo = prestamoTestDataBuilder.build();

        // assert
        assertEquals(libroEjemplo.getTitulo(), prestamo.getLibro().getTitulo());
        assertEquals(libroEjemplo.getIsbn(), prestamo.getLibro().getIsbn());
        assertEquals(libroEjemplo.getAnio(), prestamo.getLibro().getAnio());
        assertEquals(fecha, prestamo.getFechaSolicitud());
        assertEquals(NOMBRE_USUARIO, prestamo.getNombreUsuario());
    }

    @Test
    public void fechaPrestamoNormal() {

        // arrange
        Date fecha = new Date(2018,01,01);
        Date fechaMax = null;
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder();

        // act
        Prestamo prestamo = prestamoTestDataBuilder.conFechaYLibro(fecha, libroEjemplo).build();

        // assert
        assertEquals(fechaMax, prestamo.getFechaEntregaMaxima());
    }

    @Test
    public void fechaPrestamoISBNMayorTreinta() {

        // arrange
        Date fecha = new Date(2018,07,23);
        Date fechaMax = new Date(2018,8,9);
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder();

        // act
        Prestamo prestamo = prestamoTestDataBuilder.conFechaYLibro(fecha, libroIsbnMayorTreinta).build();

        // assert
        assertEquals(fechaMax, prestamo.getFechaEntregaMaxima());
    }

    @Test
    public void fechaPrestamoISBNMayorTreintaConDomingo() {

        // arrange
        Date fecha = new Date(2017,05,26);
        Date fechaMax = new Date(2017,6,12);
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder();

        // act
        Prestamo prestamo = prestamoTestDataBuilder.conFechaYLibro(fecha, libroIsbnMayorTreinta).build();

        // assert
        assertEquals(fechaMax, prestamo.getFechaEntregaMaxima());
    }
}

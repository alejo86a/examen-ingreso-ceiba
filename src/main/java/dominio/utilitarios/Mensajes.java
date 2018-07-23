package dominio.utilitarios;

public enum Mensajes {
    EXITO("El libro fue prestado exitosamente."),
    YAPRESTADO("El libro ya fue prestado, y no se puede prestar más de una vez."),
    ERRORPALINDROMO("los libros palíndromos solo se pueden utilizar en la biblioteca");

    private String texto;
    Mensajes (String msj) {
        this.texto = msj;
    }

    public String get() {
        return texto;
    }
}

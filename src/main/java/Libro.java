import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"autor", "nombre", "editorial", "isbn"})
public class Libro {

    private String nombre;
    private String autor;
    private String editorial;
    private String isbn;

    public Libro(String nombre, String autor, String editorial, String isbn) {
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

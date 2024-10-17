package persona;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(propOrder = {"nombre", "telefono", "dni", "edad"}) // Mantiene el orden de los elementos en el XML
public class Persona implements Serializable {

    private String nombre;
    private String telefono;
    private String dni;
    private int edad;

    // Required by JAXB
    public Persona() {
        // Constructor vacío requerido por JAXB
    }

    public Persona(String nombre, String telefono, int edad, String dni) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

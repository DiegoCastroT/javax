package liberia;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


@XmlRootElement
public class Libreria {

    private ArrayList<Libro> listaLibros;
    private String nombre;
    private String lugar;

    public Libreria() {

    }

    public Libreria(ArrayList<Libro> listaLibros, String nombre, String lugar) {
        this.listaLibros = listaLibros;
        this.nombre = nombre;
        this.lugar = lugar;
    }

    @XmlElementWrapper(name = "listaLibros")
    @XmlElement(name = "libro")
    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void rellenarLibreria(){
        listaLibros.add(new Libro("Libro1","Autor1","Editorial1","isbn1"));
        listaLibros.add(new Libro("Libro2","Autor2","Editorial2","isbn2"));
        listaLibros.add(new Libro("Libro3","Autor3","Editorial3","isbn3"));
        listaLibros.add(new Libro("Libro4","Autor4","Editorial4","isbn4"));
    }
}

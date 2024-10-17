package persona;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "personas") // Este será el elemento raíz del XML
public class Personas implements Serializable {

    private List<Persona> personas = new ArrayList<>();

    // Constructor vacío requerido por JAXB
    public Personas() {}

    public Personas(List<Persona> personas) {
        this.personas = personas;
    }

    @XmlElement(name = "persona") // Indica que cada elemento de la lista será un <persona>
    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}

package insitutoDB.entities;

public class ProfesorEntity {

    private String codProfesor;
    private String nombreProfesor;
    private String ciudad;

    public ProfesorEntity() {
    }

    public ProfesorEntity(String codProfesor, String nombreProfesor, String ciudad) {
        this.codProfesor = codProfesor;
        this.nombreProfesor = nombreProfesor;
        this.ciudad = ciudad;
    }

    public String getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(String codProfesor) {
        this.codProfesor = codProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

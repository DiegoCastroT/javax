package insitutoDB.entities;

public class ModuloEntity {

    private String codModulo;
    private String nombreModulo;
    private String codCiclo;
    private String curso;
    private String codProfesor;

    public ModuloEntity() {
    }

    public ModuloEntity(String codModulo, String nombreModulo, String codCiclo, String curso, String codProfesor) {
        this.codModulo = codModulo;
        this.nombreModulo = nombreModulo;
        this.codCiclo = codCiclo;
        this.curso = curso;
        this.codProfesor = codProfesor;
    }

    public String getCodModulo() {
        return codModulo;
    }

    public void setCodModulo(String codModulo) {
        this.codModulo = codModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getCodCiclo() {
        return codCiclo;
    }

    public void setCodCiclo(String codCiclo) {
        this.codCiclo = codCiclo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(String codProfesor) {
        this.codProfesor = codProfesor;
    }
}

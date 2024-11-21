package insitutoDB.entities;

import java.util.Date;

public class AlumnEntity {

    private String codAlumno;
    private String nombreAlumno;
    private String apellidosAlumno;
    private Date fechaNacimiento;
    private char grupo;

    public AlumnEntity() {

    }

    public AlumnEntity(String codAlumno, String nombreAlumno, char grupo, Date fechaNacimiento, String apellidosAlumno) {
        this.codAlumno = codAlumno;
        this.nombreAlumno = nombreAlumno;
        this.grupo = grupo;
        this.fechaNacimiento = fechaNacimiento;
        this.apellidosAlumno = apellidosAlumno;
    }

    public String getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(String codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getApellidosAlumno() {
        return apellidosAlumno;
    }

    public void setApellidosAlumno(String apellidosAlumno) {
        this.apellidosAlumno = apellidosAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }
}

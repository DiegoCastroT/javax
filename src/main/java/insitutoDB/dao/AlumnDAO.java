package insitutoDB.dao;

import insitutoDB.entities.AlumnEntity;

import java.util.List;

public interface AlumnDAO {

    public void crearAlumno(AlumnEntity alumno);
    public AlumnEntity obtenerAlumnoPorId(String id);
    public List<AlumnEntity> obtenerTodosLosAlumnos();
    public void actualizarAlumno(AlumnEntity alumnEntity);
    public boolean eliminarAlumno(String id);

}

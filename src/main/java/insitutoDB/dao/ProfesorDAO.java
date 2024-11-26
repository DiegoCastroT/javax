package insitutoDB.dao;



import insitutoDB.entities.ProfesorEntity;

import java.util.List;

public interface ProfesorDAO {

    void crearProfesor(ProfesorEntity profesor);
    ProfesorEntity obtenerProfesorPorId(String id);
    List<ProfesorEntity> obtenerTodosLosProfesores();
    void actualizarProfesor(ProfesorEntity profesor);
    boolean eliminarProfesor(String id);
}

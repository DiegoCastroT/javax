package insitutoDB.services;

import insitutoDB.dao.ProfesorDAOImplementation;
import insitutoDB.dto.ProfesorDTO;
import insitutoDB.entities.ProfesorEntity;

import javax.sql.DataSource;
import java.util.List;

public class ProfesorService {

    private static ProfesorService instance;
    private ProfesorDAOImplementation profesorDAO;

    private ProfesorService(DataSource dataSource) {
        this.profesorDAO = ProfesorDAOImplementation.getInstance(dataSource);
    }

    public static ProfesorService getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProfesorService(dataSource);
        }
        return instance;
    }

    public ProfesorDTO crearProfesor(ProfesorDTO profesorDTO) {
        ProfesorEntity profesorEntity = mapearDtoAEntidad(profesorDTO);
        profesorDAO.crearProfesor(profesorEntity);
        return mapearEntidadADto(profesorEntity);
    }

    public ProfesorDTO obtenerProfesorPorId(String id) {
        ProfesorEntity profesorEntity = profesorDAO.obtenerProfesorPorId(id);
        return mapearEntidadADto(profesorEntity);
    }

    public List<ProfesorDTO> obtenerTodosLosProfesores() {
        List<ProfesorEntity> profesorEntities = profesorDAO.obtenerTodosLosProfesores();
        return profesorEntities.stream().map(this::mapearEntidadADto).toList();
    }

    public ProfesorDTO actualizarProfesor(ProfesorDTO profesorDTO) {
        ProfesorEntity profesorEntity = mapearDtoAEntidad(profesorDTO);
        profesorDAO.actualizarProfesor(profesorEntity);
        return mapearEntidadADto(profesorEntity);
    }

    public boolean eliminarProfesor(String id) {
        return profesorDAO.eliminarProfesor(id);
    }

    private ProfesorDTO mapearEntidadADto(ProfesorEntity profesorEntity) {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setIdProfesor(profesorEntity.getCodProfesor());
        profesorDTO.setNombreProfesor(profesorEntity.getNombreProfesor());
        profesorDTO.setCiudad(profesorEntity.getCiudad());
        return profesorDTO;
    }

    private ProfesorEntity mapearDtoAEntidad(ProfesorDTO profesorDTO) {
        ProfesorEntity profesorEntity = new ProfesorEntity();
        profesorEntity.setCodProfesor(profesorDTO.getIdProfesor());
        profesorEntity.setNombreProfesor(profesorDTO.getNombreProfesor());
        profesorEntity.setCiudad(profesorDTO.getCiudad());
        return profesorEntity;
    }
}

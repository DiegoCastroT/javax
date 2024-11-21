package insitutoDB.services;

import insitutoDB.dao.AlumnDAOImplementation;
import insitutoDB.dto.AlumnDTO;
import insitutoDB.entities.AlumnEntity;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlumnService {

    private static AlumnService instance;
    private AlumnDAOImplementation alumnDAOImplementation;

    // Constructor privado para crear el servicio con un DataSource
    private AlumnService(DataSource dataSource) {
        this.alumnDAOImplementation = AlumnDAOImplementation.getInstance(dataSource);
    }

    // Método para obtener la instancia del servicio (Singleton)
    public static AlumnService getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new AlumnService(dataSource);
        }
        return instance;
    }

    // Crear un nuevo alumno
    public AlumnDTO crearAlumno(AlumnDTO alumnDTO) {
        AlumnEntity alumnEntity = mapearEntidadADto(alumnDTO);  // Mapea DTO a Entity
        alumnDAOImplementation.crearAlumno(alumnEntity);  // Llama al DAO para crear el alumno
        return mapearEntidadDTO(alumnEntity);  // Devuelve el DTO con los datos del alumno
    }

    // Obtener un alumno por su ID
    public AlumnDTO obtenerAlumnoPorId(String id) {
        AlumnEntity alumnEntity = alumnDAOImplementation.obtenerAlumnoPorId(id);  // Llama al DAO
        return mapearEntidadDTO(alumnEntity);  // Mapea la entidad a un DTO
    }

    // Obtener todos los alumnos
    public List<AlumnDTO> obtenerTodosLosAlumnos() {
        List<AlumnEntity> alumnEntities = alumnDAOImplementation.obtenerTodosLosAlumnos();  // Llama al DAO
        // Mapea la lista de entidades a DTOs
        return alumnEntities.stream().map(this::mapearEntidadDTO).toList();
    }

    // Actualizar los datos de un alumno
    public AlumnDTO actualizarAlumno(AlumnDTO alumnDTO) {
        AlumnEntity alumnEntity = mapearEntidadADto(alumnDTO);  // Mapea DTO a Entity
        alumnDAOImplementation.actualizarAlumno(alumnEntity);  // Llama al DAO para actualizar el alumno
        return mapearEntidadDTO(alumnEntity);  // Devuelve el DTO actualizado
    }

    // Eliminar un alumno
    public boolean eliminarAlumno(AlumnDTO alumnDTO) {
        AlumnEntity alumnEntity = mapearEntidadADto(alumnDTO);  // Mapea DTO a Entity
        return alumnDAOImplementation.eliminarAlumno(alumnEntity.getNombreAlumno());  // Llama al DAO para eliminar el alumno
    }

    // Mapea un DTO a una entidad
    private AlumnEntity mapearEntidadADto(AlumnDTO alumnDTO) {
        AlumnEntity alumnEntity = new AlumnEntity();
        alumnEntity.setCodAlumno(alumnDTO.getIdAlumno());
        alumnEntity.setNombreAlumno(alumnDTO.getNombreAlumno());
        alumnEntity.setApellidosAlumno(alumnDTO.getApellidosAlumno());
        alumnEntity.setGrupo(alumnDTO.getGrupo());
        return alumnEntity;
    }

    // Mapea una entidad a un DTO
    private AlumnDTO mapearEntidadDTO(AlumnEntity alumnEntity) {
        AlumnDTO alumnDTO = new AlumnDTO();
        alumnDTO.setIdAlumno(alumnEntity.getCodAlumno());  // Corregido: setIdAlumno en lugar de getIdAlumno
        alumnDTO.setNombreAlumno(alumnEntity.getNombreAlumno());
        alumnDTO.setApellidosAlumno(alumnEntity.getApellidosAlumno());
        alumnDTO.setGrupo(alumnEntity.getGrupo());
        // Calcular la edad a partir de la fecha de nacimiento y agregarla al DTO
        alumnDTO.setEdad(calcularEdad(alumnEntity.getFechaNacimiento()));  // Calcula la edad
        return alumnDTO;
    }

    // Calcula la edad de un alumno basado en su fecha de nacimiento
    private int calcularEdad(Date fechaNacimiento) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);  // Año actual
        calendar.setTime(fechaNacimiento);
        int birthYear = calendar.get(Calendar.YEAR);  // Año de nacimiento
        return currentYear - birthYear;  // Edad estimada
    }

    // Convierte una edad en un objeto Date (aproximadamente)
    private Date convertirEdadAFecha(int edad) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -edad);  // Resta los años de la edad
        return calendar.getTime();
    }
}

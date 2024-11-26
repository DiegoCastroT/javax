package insitutoDB.services;

import insitutoDB.dao.ModuloDAOImplementation;
import insitutoDB.dto.ModuloDTO;
import insitutoDB.entities.ModuloEntity;

import javax.sql.DataSource;
import java.util.List;

public class ModuloService {

    private static ModuloService instance;
    private ModuloDAOImplementation moduloDAO;

    private ModuloService(DataSource dataSource) {
        this.moduloDAO = ModuloDAOImplementation.getInstance(dataSource);
    }

    public static ModuloService getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ModuloService(dataSource);
        }
        return instance;
    }

    public ModuloDTO crearModulo(ModuloDTO moduloDTO) {
        ModuloEntity moduloEntity = mapearDtoAEntidad(moduloDTO);
        moduloDAO.crearModulo(moduloEntity);
        return mapearEntidadADto(moduloEntity);
    }

    public ModuloDTO obtenerModuloPorId(String id) {
        ModuloEntity moduloEntity = moduloDAO.obtenerModuloPorId(id);
        return mapearEntidadADto(moduloEntity);
    }

    public List<ModuloDTO> obtenerTodosLosModulos() {
        List<ModuloEntity> moduloEntities = moduloDAO.obtenerTodosLosModulos();
        return moduloEntities.stream().map(this::mapearEntidadADto).toList();
    }

    public ModuloDTO actualizarModulo(ModuloDTO moduloDTO) {
        ModuloEntity moduloEntity = mapearDtoAEntidad(moduloDTO);
        moduloDAO.actualizarModulo(moduloEntity);
        return mapearEntidadADto(moduloEntity);
    }

    public boolean eliminarModulo(String id) {
        return moduloDAO.eliminarModulo(id);
    }

    private ModuloDTO mapearEntidadADto(ModuloEntity moduloEntity) {
        ModuloDTO moduloDTO = new ModuloDTO();
        moduloDTO.setIdModulo(moduloEntity.getCodModulo());
        moduloDTO.setNombreModulo(moduloEntity.getNombreModulo());
        moduloDTO.setCodCiclo(moduloEntity.getCodCiclo());
        moduloDTO.setCurso(moduloEntity.getCurso());
        moduloDTO.setIdProfesor(moduloEntity.getCodProfesor());
        return moduloDTO;
    }

    private ModuloEntity mapearDtoAEntidad(ModuloDTO moduloDTO) {
        ModuloEntity moduloEntity = new ModuloEntity();
        moduloEntity.setCodModulo(moduloDTO.getIdModulo());
        moduloEntity.setNombreModulo(moduloDTO.getNombreModulo());
        moduloEntity.setCodCiclo(moduloDTO.getCodCiclo());
        moduloEntity.setCurso(moduloDTO.getCurso());
        moduloEntity.setCodProfesor(moduloDTO.getIdProfesor());
        return moduloEntity;
    }
}

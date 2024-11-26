package insitutoDB.dao;
import insitutoDB.entities.ModuloEntity;
import java.util.List;

public interface ModuloDAO {

    void crearModulo(ModuloEntity modulo);
    ModuloEntity obtenerModuloPorId(String id);
    List<ModuloEntity> obtenerTodosLosModulos();
    void actualizarModulo(ModuloEntity modulo);
    boolean eliminarModulo(String id);

}

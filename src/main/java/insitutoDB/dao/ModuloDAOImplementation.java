package insitutoDB.dao;

import insitutoDB.entities.ModuloEntity;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAOImplementation implements ModuloDAO {

    private static ModuloDAOImplementation instance;
    private DataSource dataSource;

    private ModuloDAOImplementation(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ModuloDAOImplementation getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ModuloDAOImplementation(dataSource);
        }
        return instance;
    }

    @Override
    public void crearModulo(ModuloEntity modulo) {
        String sql = "INSERT INTO modulo (cod_modulo, nombre_modulo, cod_ciclo, curso, cod_profesor) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, modulo.getCodModulo());
            statement.setString(2, modulo.getNombreModulo());
            statement.setString(3, modulo.getCodCiclo());
            statement.setString(4, modulo.getCurso());
            statement.setString(5, modulo.getCodProfesor());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ModuloEntity obtenerModuloPorId(String id) {
        String sql = "SELECT cod_modulo, nombre_modulo, cod_ciclo, curso, cod_profesor FROM modulo WHERE cod_modulo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearResultSetAModulo(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<ModuloEntity> obtenerTodosLosModulos() {
        List<ModuloEntity> modulos = new ArrayList<>();
        String sql = "SELECT cod_modulo, nombre_modulo, cod_ciclo, curso, cod_profesor FROM modulo";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                modulos.add(mapearResultSetAModulo(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return modulos;
    }

    @Override
    public void actualizarModulo(ModuloEntity modulo) {
        String sql = "UPDATE modulo SET nombre_modulo = ?, cod_ciclo = ?, curso = ?, cod_profesor = ? WHERE cod_modulo = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, modulo.getNombreModulo());
            statement.setString(2, modulo.getCodCiclo());
            statement.setString(3, modulo.getCurso());
            statement.setString(4, modulo.getCodProfesor());
            statement.setString(5, modulo.getCodModulo());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean eliminarModulo(String id) {
        String sql = "DELETE FROM modulo WHERE cod_modulo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModuloEntity mapearResultSetAModulo(ResultSet resultSet) throws SQLException {
        ModuloEntity modulo = new ModuloEntity();
        modulo.setCodModulo(resultSet.getString("cod_modulo"));
        modulo.setNombreModulo(resultSet.getString("nombre_modulo"));
        modulo.setCodCiclo(resultSet.getString("cod_ciclo"));
        modulo.setCurso(resultSet.getString("curso"));
        modulo.setCodProfesor(resultSet.getString("cod_profesor"));
        return modulo;
    }
}

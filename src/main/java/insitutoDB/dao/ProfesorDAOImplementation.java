package insitutoDB.dao;
import insitutoDB.entities.ProfesorEntity;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAOImplementation implements ProfesorDAO {

    private static ProfesorDAOImplementation instance;
    private DataSource dataSource;

    private ProfesorDAOImplementation(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProfesorDAOImplementation getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ProfesorDAOImplementation(dataSource);
        }
        return instance;
    }

    @Override
    public void crearProfesor(ProfesorEntity profesor) {
        String sql = "INSERT INTO profesor (cod_profesor, nombre_profesor, ciudad) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, profesor.getCodProfesor());
            statement.setString(2, profesor.getNombreProfesor());
            statement.setString(3, profesor.getCiudad());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProfesorEntity obtenerProfesorPorId(String id) {
        String sql = "SELECT cod_profesor, nombre_profesor, ciudad FROM profesor WHERE cod_profesor = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearResultSetAProfesor(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<ProfesorEntity> obtenerTodosLosProfesores() {
        List<ProfesorEntity> profesores = new ArrayList<>();
        String sql = "SELECT cod_profesor, nombre_profesor, ciudad FROM profesor";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                profesores.add(mapearResultSetAProfesor(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profesores;
    }

    @Override
    public void actualizarProfesor(ProfesorEntity profesor) {
        String sql = "UPDATE profesor SET nombre_profesor = ?, ciudad = ? WHERE cod_profesor = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, profesor.getNombreProfesor());
            statement.setString(2, profesor.getCiudad());
            statement.setString(3, profesor.getCodProfesor());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean eliminarProfesor(String id) {
        String sql = "DELETE FROM profesor WHERE cod_profesor = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, id);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ProfesorEntity mapearResultSetAProfesor(ResultSet resultSet) throws SQLException {
        ProfesorEntity profesor = new ProfesorEntity();
        profesor.setCodProfesor(resultSet.getString("cod_profesor"));
        profesor.setNombreProfesor(resultSet.getString("nombre_profesor"));
        profesor.setCiudad(resultSet.getString("ciudad"));
        return profesor;
    }
}

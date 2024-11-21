package insitutoDB.dao;

import insitutoDB.entities.AlumnEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnDAOImplementation implements AlumnDAO {

    private static AlumnDAOImplementation instance;
    private DataSource dataSource;

    //Constructor
    private AlumnDAOImplementation(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static AlumnDAOImplementation getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new AlumnDAOImplementation(dataSource);
        }
        return instance;
    }

    public void crearAlumno(AlumnEntity alumnEntity) {
        String sql = "INSERT INTO alumno (cod_alumno, nombre_alumno, apellidos_alumno," +
                "fecha_nacimiento, grupo) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, alumnEntity.getCodAlumno());
            statement.setString(2, alumnEntity.getNombreAlumno());
            statement.setString(3, alumnEntity.getApellidosAlumno());
            statement.setDate(4, new java.sql.Date(alumnEntity.getFechaNacimiento().getTime()));
            statement.setString(5, String.valueOf(alumnEntity.getGrupo()));
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public AlumnEntity obtenerAlumnoPorId(String id) {
        String sql = "SELECT cod_alumno, nombre_alumno, apellidos_alumno,fecha_nacimiento," +
                " grupo FROM alumno WHERE cod_alumno = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapearResultSetAAlumno(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private AlumnEntity mapearResultSetAAlumno(ResultSet resultSet) throws SQLException {
        AlumnEntity alumno = new AlumnEntity();
        alumno.setCodAlumno(resultSet.getString("cod_alumno"));
        alumno.setNombreAlumno(resultSet.getString("nombre_alumno"));
        alumno.setApellidosAlumno(resultSet.getString("apellidos_alumno"));
        alumno.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
        alumno.setGrupo(resultSet.getString("grupo").charAt(0));
        return alumno;
    }

    public List<AlumnEntity> obtenerTodosLosAlumnos() {
        List<AlumnEntity> alumnos = new ArrayList<>();
        String sql = "SELECT cod_alumno, nombre_alumno, apellidos_alumno, fecha_nacimiento, grupo FROM alumno";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                AlumnEntity alumno = mapearResultSetAAlumno(resultSet);
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void actualizarAlumno(AlumnEntity alumno) {
        String sql = "UPDATE alumno SET nombre_alumno = ?, apellidos_alumno = ?, fecha_nacimiento = ?, grupo = ? WHERE cod_alumno = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, alumno.getNombreAlumno());
            statement.setString(2, alumno.getApellidosAlumno());
            statement.setDate(3, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            statement.setString(4, String.valueOf(alumno.getGrupo()));
            statement.setString(5, alumno.getCodAlumno());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean eliminarAlumno(String id) {
        String sql = "DELETE FROM alumno WHERE cod_alumno = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
return false;
    }
}

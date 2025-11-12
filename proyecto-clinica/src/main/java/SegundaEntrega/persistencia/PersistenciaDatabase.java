package SegundaEntrega.persistencia;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;

public class PersistenciaDatabase implements AsociadoDAO {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; 
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Grupo_10";
    private static final String USER = "progra_c"; 
    private static final String PASS = "progra_c"; 

    private Connection conexion = null;

    private void conectar() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (Exception e) {
            System.out.println("No se pudo cargar el driver JDBC: " + e.getMessage());
        }
        conexion = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private void cerrarConexion(Statement sentencia, ResultSet resultado) {
        try {
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException e) {
            // Ignorar o manejar el error de cierre
        }
    }


    @Override
    public AsociadoDTO getAsociadobyDNI(String dni) {
        PreparedStatement ps = null; // Usamos PreparedStatement por seguridad
        ResultSet resultado = null;
        AsociadoDTO asociado = null;

        try {
            conectar();
            String sql = "SELECT * FROM asociados WHERE dni = ?"; 
            ps = conexion.prepareStatement(sql);
            ps.setString(1, dni);

            resultado = ps.executeQuery();

            if (resultado.next()) { 
                String nombre = resultado.getString("nombre");
                boolean alta = resultado.getBoolean("alta");
                asociado = new AsociadoDTO(nombre, alta, dni); 
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al consultar el asociado: " + e.getMessage());
        } finally {
            cerrarConexion(ps, resultado);
        }
        return asociado;
    }

    @Override
    public List<AsociadoDTO> getAllAsociados() {
        Statement sentencia = null;
        ResultSet resultado = null;
        List<AsociadoDTO> listaAsociados = new ArrayList<>();

        try {
            conectar();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery("SELECT * FROM asociados");

            while (resultado.next()) { // Itera sobre todos los resultados [cite: 175]
                String nombre = resultado.getString("nombre");
                boolean alta = resultado.getBoolean("alta");
                String dni = resultado.getString("dni");
                AsociadoDTO asociado = new AsociadoDTO(nombre, alta, dni); 
                listaAsociados.add(asociado);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al consultar todos los asociados: " + e.getMessage());
        } finally {
            cerrarConexion(sentencia, resultado);
        }
        return listaAsociados;
    }

    @Override
    public AsociadoDTO createAsociado(AsociadoDTO asociado) {
        PreparedStatement ps = null;
        try {
            conectar();
            String sql = "INSERT INTO asociados (nombre, dni, alta) VALUES (?, ?, ?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, asociado.getNombre());
            ps.setString(2, asociado.getDni()); 
            ps.setBoolean(3, asociado.isAlta());
            ps.execute(); 

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
            return null;
        } finally {
            cerrarConexion(ps, null);
        }
        return asociado;
    }

    @Override
    public void darDeBajaAsociado(long id) {
        PreparedStatement ps = null;

        try {
            conectar();
            String sql = "DELETE FROM asociados WHERE id = ?"; // Se asume una columna ID
            ps = conexion.prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al dar de baja al asociado: " + e.getMessage());
        } finally {
            cerrarConexion(ps, null);
        }
    }
}

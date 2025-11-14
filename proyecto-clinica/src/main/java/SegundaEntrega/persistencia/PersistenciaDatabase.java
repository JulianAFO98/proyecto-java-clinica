package SegundaEntrega.persistencia;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;

public class PersistenciaDatabase implements AsociadoDAO {

    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/Grupo_10";
    private static final String USER = "progra_c";
    private static final String PASS = "progra_c";


  
    private Connection obtenerConexion() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER); 
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver JDBC: " + e.getMessage(), e);
        }
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
   
    private void cerrarRecursos(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar ResultSet: " + e.getMessage());
        }

        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar Statement: " + e.getMessage());
        }

        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar Connection: " + e.getMessage());
        }
    }


    @Override
    public AsociadoDTO getAsociadobyDNI(String dni) {
        Connection conn = null;
        PreparedStatement ps = null; 
        ResultSet resultado = null;
        AsociadoDTO asociado = null;
        String sql = "SELECT nombre, alta, dni FROM asociados WHERE dni = ?";

        try {
            conn = obtenerConexion(); 
            ps = conn.prepareStatement(sql); 
            ps.setString(1, dni);

            resultado = ps.executeQuery(); 

            if (resultado.next()) {
                String nombre = resultado.getString("nombre");
                boolean alta = resultado.getBoolean("alta");
                String dniEnDB = resultado.getString("dni"); 
                asociado = new AsociadoDTO(nombre, alta, dniEnDB);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar el asociado: " + e.getMessage());
        } finally {
            cerrarRecursos(resultado, ps, conn); 
        }
        return asociado;
    }


    @Override
    public List<AsociadoDTO> getAllAsociados() {
        Connection conn = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        List<AsociadoDTO> listaAsociados = new ArrayList<>();
        String sql = "SELECT * FROM asociados";

        try {
            conn = obtenerConexion();
            sentencia = conn.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                boolean alta = resultado.getBoolean("alta");
                String dni = resultado.getString("dni");
                int id = resultado.getInt("id");
                AsociadoDTO asociado = new AsociadoDTO(id,nombre, alta, dni);
                listaAsociados.add(asociado);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar todos los asociados: " + e.getMessage());
        } finally {
            cerrarRecursos(resultado, sentencia, conn);
        }
        return listaAsociados;
    }

    
    @Override
    public AsociadoDTO createAsociado(AsociadoDTO asociado) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO asociados (nombre, dni, alta) VALUES (?, ?, ?)";
        
        try {
            conn = obtenerConexion();
            ps = conn.prepareStatement(sql);

            ps.setString(1, asociado.getNombre());
            ps.setString(2, asociado.getDni());
            ps.setBoolean(3, asociado.isAlta());
            ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
            return null;
        } finally {
            cerrarRecursos(null, ps, conn); 
        }
        return asociado;
    }


    @Override
    public void darDeBajaAsociado(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM asociados WHERE id = ?"; 

        try {
            conn = obtenerConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Error al dar de baja al asociado: " + e.getMessage());
        } finally {
            cerrarRecursos(null, ps, conn); 
        }
    }
}
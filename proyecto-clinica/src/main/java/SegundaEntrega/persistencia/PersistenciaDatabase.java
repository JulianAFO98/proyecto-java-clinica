package SegundaEntrega.persistencia;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementacion de acceso a datos utilizando JDBC y MariaDB.
 */
public class PersistenciaDatabase implements AsociadoDAO {

    
    /**
     * Busca un asociado por su dni dentro de la tabla.
     *
     * @param dni identificador a consultar
     * @return dto del asociado encontrado o null si no existe
     */
    @Override
    public AsociadoDTO getAsociadobyDNI(String dni) {
        Connection conn = null;
        PreparedStatement ps = null; 
        ResultSet resultado = null;
        AsociadoDTO asociado = null;
        String sql = "SELECT nombre, alta, dni FROM asociados WHERE dni = ?";

        try {
            conn = Conexion.obtenerConexion(); 
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
            Conexion.cerrarRecursos(resultado, ps, conn); 
        }
        return asociado;
    }


    /**
     * Recupera todos los asociados registrados.
     *
     * @return lista completa de asociados DTO
     */
    @Override
    public List<AsociadoDTO> getAllAsociados() {
        Connection conn = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        List<AsociadoDTO> listaAsociados = new ArrayList<>();
        String sql = "SELECT * FROM asociados";

        try {
            conn = Conexion.obtenerConexion();
            sentencia = conn.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                boolean alta = resultado.getBoolean("alta");
                String dni = resultado.getString("dni");
                int id = resultado.getInt("id");
                AsociadoDTO asociado = new AsociadoDTO(id,dni, alta, nombre);
                listaAsociados.add(asociado);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar todos los asociados: " + e.getMessage());
        } finally {
            Conexion.cerrarRecursos(resultado, sentencia, conn);
        }
        return listaAsociados;
    }

    
    /**
     * Persiste un asociado y devuelve el objeto utilizado en la insercion.
     *
     * @param asociado dto con los datos a guardar
     * @return mismo objeto dto si la insercion fue exitosa o null si fallo
     */
    @Override
    public AsociadoDTO createAsociado(AsociadoDTO asociado) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO asociados (nombre, dni, alta) VALUES (?, ?, ?)";
        
        try {
            conn = Conexion.obtenerConexion();
            ps = conn.prepareStatement(sql);

            ps.setString(1, asociado.getNombre());
            ps.setString(2, asociado.getDni());
            ps.setBoolean(3, asociado.isAlta());
            ps.execute();
            
        } catch (SQLException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
            return null;
        } finally {
            Conexion.cerrarRecursos(null, ps, conn); 
        }
        return asociado;
    }


    /**
     * Elimina un asociado por identificador primario.
     *
     * @param id clave del registro a eliminar
     */
    @Override
    public void darDeBajaAsociado(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM asociados WHERE id = ?"; 

        try {
            conn = Conexion.obtenerConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Error al dar de baja al asociado: " + e.getMessage());
        } finally {
            Conexion.cerrarRecursos(null, ps, conn); 
        }
    }
}
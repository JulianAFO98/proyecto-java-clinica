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
     * <br>Pre: dni != null y no vacio
     * <br>Post: Retorna un AsociadoDTO o null si no se encuentra. Si retorna un objeto, su DNI coincide con el dni de entrada.
     * @return dto del asociado encontrado o null si no existe
     */
    @Override
    public AsociadoDTO getAsociadobyDNI(String dni) {
        // Precondicion
        assert dni != null && !dni.trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        
        Connection conn = null;
        PreparedStatement ps = null; 
        ResultSet resultado = null;
        AsociadoDTO asociado = null;
        String sql = "SELECT id, nombre, alta, dni FROM asociados WHERE dni = ?"; // Se incluye 'id' para construir el DTO completo

        try {
            conn = Conexion.obtenerConexion(); 
            // Invariante: La conexion debe ser valida y estar abierta
            assert conn != null && !conn.isClosed() : "La conexion debe ser valida y estar abierta.";
            
            ps = conn.prepareStatement(sql); 
            ps.setString(1, dni);

            resultado = ps.executeQuery(); 

            if (resultado.next()) {
                int id = resultado.getInt("id"); // Obtener el ID
                String nombre = resultado.getString("nombre");
                boolean alta = resultado.getBoolean("alta");
                String dniEnDB = resultado.getString("dni"); 
                asociado = new AsociadoDTO(id, dniEnDB, alta, nombre); // Usar el constructor con ID
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar el asociado: " + e.getMessage());
        } finally {
            Conexion.cerrarRecursos(resultado, ps, conn); 
        }
        
        // Postcondicion
        if (asociado != null) {
            assert asociado.getDni().equals(dni) : "El DNI del asociado retornado no coincide con el DNI de busqueda.";
        }
        
        return asociado;
    }


    /**
     * Recupera todos los asociados registrados.
     *
     * @return lista completa de asociados DTO
     * <br>Pre: No aplica
     * <br>Post: Retorna una lista no nula de AsociadoDTO.
     */
    @Override
    public List<AsociadoDTO> getAllAsociados() {
        Connection conn = null;
        Statement sentencia = null;
        ResultSet resultado = null;
        List<AsociadoDTO> listaAsociados = new ArrayList<>();
        String sql = "SELECT id, nombre, alta, dni FROM asociados"; // Se explicitan las columnas

        try {
            conn = Conexion.obtenerConexion();
            // Invariante: La conexion debe ser valida y estar abierta
            assert conn != null && !conn.isClosed() : "La conexion debe ser valida y estar abierta.";

            sentencia = conn.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                boolean alta = resultado.getBoolean("alta");
                String dni = resultado.getString("dni");
                int id = resultado.getInt("id");
                
                // Invariante de ciclo: Los datos leidos de la DB no deben ser nulos
                assert nombre != null : "El nombre leido no debe ser nulo.";
                assert dni != null : "El DNI leido no debe ser nulo.";
                assert id >= 0 : "El ID leido debe ser no negativo.";
                
                AsociadoDTO asociado = new AsociadoDTO(id, dni, alta, nombre);
                listaAsociados.add(asociado);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar todos los asociados: " + e.getMessage());
        } finally {
            Conexion.cerrarRecursos(resultado, sentencia, conn);
        }
        
        // Postcondicion
        assert listaAsociados != null : "La lista de asociados retornado no debe ser nula.";

        return listaAsociados;
    }

    
    /**
     * Persiste un asociado y devuelve el objeto utilizado en la insercion.
     *
     * @param asociado dto con los datos a guardar
     * <br>Pre: asociado != null, asociado.getDni() y asociado.getNombre() no nulos/vacios.
     * <br>Post: Retorna el mismo objeto asociado si la insercion fue exitosa.
     * @return mismo objeto dto si la insercion fue exitosa o null si fallo
     */
    @Override
    public AsociadoDTO createAsociado(AsociadoDTO asociado) {
        // Precondiciones
        assert asociado != null : "El asociado no puede ser nulo.";
        assert asociado.getNombre() != null && !asociado.getNombre().trim().isEmpty() : "El nombre no debe ser nulo o vacio.";
        assert asociado.getDni() != null && !asociado.getDni().trim().isEmpty() : "El DNI no debe ser nulo o vacio.";
        
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO asociados (nombre, dni, alta) VALUES (?, ?, ?)";
        int rowsAffected = 0;
        
        try {
            conn = Conexion.obtenerConexion();
            // Invariante: La conexion debe ser valida y estar abierta
            assert conn != null && !conn.isClosed() : "La conexion debe ser valida y estar abierta.";

            ps = conn.prepareStatement(sql);

            ps.setString(1, asociado.getNombre());
            ps.setString(2, asociado.getDni());
            ps.setBoolean(3, asociado.isAlta());
            
            rowsAffected = ps.executeUpdate(); // Usar executeUpdate para obtener las filas afectadas
            
            // Postcondicion de exito (se inserto exactamente 1 fila)
            assert rowsAffected == 1 : "La insercion debe afectar exactamente 1 fila.";
            
        } catch (SQLException e) {
            System.out.println("Error al insertar datos: " + e.getMessage());
            return null; // Retorna null en caso de falla (como lo hacia el original)
        } finally {
            Conexion.cerrarRecursos(null, ps, conn); 
        }
        return asociado;
    }


    /**
     * Da de baja (actualiza 'alta' a false) a un asociado por identificador primario.
     *
     * @param id clave del registro a dar de baja
     * <br>Pre: id > 0
     * <br>Post: El registro asociado al 'id' tiene el campo 'alta' en false.
     */
    @Override
    public void darDeBajaAsociado(int id) {
        // Precondicion
        assert id > 0 : "El ID debe ser positivo.";

        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE asociados SET alta = FALSE WHERE id = ?"; 
        int rowsAffected = 0;

        try {
            conn = Conexion.obtenerConexion();
            // Invariante: La conexion debe ser valida y estar abierta
            assert conn != null && !conn.isClosed() : "La conexion debe ser valida y estar abierta.";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            rowsAffected = ps.executeUpdate(); // Usar executeUpdate para obtener las filas afectadas
            
            // Postcondicion: Si la operacion fue exitosa, se afecto 1 fila (el asociado existia)
            // No podemos forzar un assert rowsAffected == 1 ya que el asociado podria no existir.
            
        } catch (SQLException e) {
            System.out.println("Error al dar de baja al asociado: " + e.getMessage());
        } finally {
            Conexion.cerrarRecursos(null, ps, conn); 
        }
    }
}
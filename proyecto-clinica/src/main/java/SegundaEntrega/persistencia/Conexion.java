package SegundaEntrega.persistencia;
import java.sql.*;


public  class Conexion {
    
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/Grupo_10";
    private static final String USER = "progra_c";
    private static final String PASS = "progra_c";
    
    private static final String ROOT_USER = "root";
    private static final String ROOT_PASS = "root";
    private static final String DB_NAME = "Grupo_10";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER); 
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver JDBC: " + e.getMessage(), e);
        }
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
    
    /**
     * Crea la base de datos utilizando credenciales de root.
     * Se conecta al servidor sin especificar base de datos y ejecuta el comando CREATE DATABASE IF NOT EXISTS.
     * 
     * @throws SQLException si ocurre un error al crear la base de datos
     */
    public static void crearBaseDatos() throws SQLException {
        String rootUrl = "jdbc:mariadb://localhost:3306/";
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(rootUrl, ROOT_USER, ROOT_PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(createDatabaseSQL);
            System.out.println("Base de datos '" + DB_NAME + "' creada exitosamente.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver JDBC: " + e.getMessage(), e);
        } finally {
            cerrarRecursos(null, stmt, conn);
        }
        
    }
    
    /**
     * Crea el usuario si no existe con permisos en la base de datos.
     * Utiliza credenciales de root para crear el usuario 'progra_c' con contraseña 'progra_c'.
     * 
     * @throws SQLException si ocurre un error al crear el usuario
     */
    public static void crearUsuario() throws SQLException {
        String rootUrl = "jdbc:mariadb://localhost:3306/";
        String createUserSQL = "CREATE USER IF NOT EXISTS '" + USER + "'@'localhost' IDENTIFIED BY '" + PASS + "';";
        String grantPermsSQL = "GRANT ALL PRIVILEGES ON " + DB_NAME + ".* TO '" + USER + "'@'localhost';";
        String flushSQL = "FLUSH PRIVILEGES;";
        
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(rootUrl, ROOT_USER, ROOT_PASS);
            stmt = conn.createStatement();
            
            stmt.executeUpdate(createUserSQL);
            System.out.println("Usuario '" + USER + "' creado exitosamente.");
            
            stmt.executeUpdate(grantPermsSQL);
            System.out.println("Permisos otorgados a '" + USER + "'.");
            
            stmt.executeUpdate(flushSQL);
            System.out.println("Privilegios refezcados.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver JDBC: " + e.getMessage(), e);
        } finally {
            cerrarRecursos(null, stmt, conn);
        }
    }
    
    /**
     * Limpia todos los datos de la tabla asociados y la recrear.
     * Si la tabla existe, la elimina y luego crea una nueva con la estructura requerida.
     * 
     * @throws SQLException si ocurre un error al limpiar o crear la tabla
     */
    public static void limpiarYCrearTabla() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.createStatement();
            
            // Eliminar la tabla si existe
            String dropTableSQL = "DROP TABLE IF EXISTS asociados;";
            stmt.executeUpdate(dropTableSQL);
            System.out.println("Tabla 'asociados' eliminada (si existía).");
            
            // Crear la tabla con la estructura requerida
            String createTableSQL = "CREATE TABLE asociados (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(255) NOT NULL, " +
                    "dni VARCHAR(20) NOT NULL, " +
                    "alta BOOLEAN NOT NULL DEFAULT TRUE" +
                    ");";
            stmt.executeUpdate(createTableSQL);
            System.out.println("Tabla 'asociados' creada exitosamente.");
            
        } catch (SQLException e) {
            System.out.println("Error al limpiar/crear la tabla: " + e.getMessage());
            throw e;
        } finally {
            cerrarRecursos(null, stmt, conn);
        }
    }
    
    /**
     * Inserta 3 asociados de prueba en la tabla asociados.
     * 
     * @throws SQLException si ocurre un error al insertar los asociados
     */
    public static void insertarAsociados() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO asociados (nombre, dni, alta) VALUES (?, ?, ?)";
        
        try {
            conn = obtenerConexion();
            ps = conn.prepareStatement(sql);
            
            // Primer asociado
            ps.setString(1, "Juan Pérez");
            ps.setString(2, "12345678A");
            ps.setBoolean(3, true);
            ps.executeUpdate();
            
            // Segundo asociado
            ps.setString(1, "María García");
            ps.setString(2, "87654321B");
            ps.setBoolean(3, true);
            ps.executeUpdate();
            
            // Tercer asociado
            ps.setString(1, "Carlos López");
            ps.setString(2, "11223344C");
            ps.setBoolean(3, true);
            ps.executeUpdate();
            
            System.out.println("3 asociados insertados exitosamente.");
            
        } catch (SQLException e) {
            System.out.println("Error al insertar asociados: " + e.getMessage());
            throw e;
        } finally {
            cerrarRecursos(null, ps, conn);
        }
    }
   
    public static void cerrarRecursos(ResultSet rs, Statement stmt, Connection conn) {
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
}

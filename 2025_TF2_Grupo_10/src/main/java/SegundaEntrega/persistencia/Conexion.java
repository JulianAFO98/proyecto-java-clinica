package SegundaEntrega.persistencia;
import java.sql.*;


/**
 * Gestiona las conexiones con MariaDB y las tareas de inicializacion del esquema.
 */
public  class Conexion {
    
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/Grupo_10";
    private static final String USER = "progra_c";
    private static final String PASS = "progra_c";
    
    private static final String ROOT_USER = "root";
    private static final String ROOT_PASS = "root";
    private static final String DB_NAME = "Grupo_10";

    /**
     * Obtiene una conexion activa hacia la base de datos de la aplicacion.
     *
     * @return conexion JDBC lista para usarse
     * <br>Pre: Las constantes de conexion (DRIVER, URL, USER, PASS) son validas.
     * <br>Post: Retorna un objeto Connection no nulo y abierto.
     * @throws SQLException si falla la carga del driver o la autenticacion
     */
    public static Connection obtenerConexion() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER); 
        } catch (ClassNotFoundException e) {
            // Postcondicion de falla: se lanza SQLException
            throw new SQLException("Error al cargar el driver JDBC: " + e.getMessage(), e);
        }
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        
        // Postcondicion de exito
        assert conn != null : "La conexion obtenida no debe ser nula.";
        assert !conn.isClosed() : "La conexion obtenida debe estar abierta.";
        
        return conn;
    }
    
    /**
     * Crea la base de datos utilizando credenciales de root.
     * Se conecta al servidor sin especificar base de datos y ejecuta el comando CREATE DATABASE IF NOT EXISTS.
     * * @throws SQLException si ocurre un error al crear la base de datos
     * <br>Pre: Las credenciales de ROOT son correctas y el servidor esta disponible.
     * <br>Post: La base de datos DB_NAME existe en el servidor.
     */
    public static void crearBaseDatos() throws SQLException {
        String rootUrl = "jdbc:mariadb://localhost:3306/";
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(rootUrl, ROOT_USER, ROOT_PASS);
            
            // Invariante: La conexion de root no debe ser nula
            assert conn != null : "La conexion de root no debe ser nula.";

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
     * * @throws SQLException si ocurre un error al crear el usuario
     * <br>Pre: Las credenciales de ROOT son correctas y el servidor esta disponible.
     * <br>Post: El usuario USER existe y tiene privilegios en la base de datos DB_NAME.
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
            
            // Invariante: La conexion de root no debe ser nula
            assert conn != null : "La conexion de root no debe ser nula.";

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
     * * @throws SQLException si ocurre un error al limpiar o crear la tabla
     * <br>Pre: La base de datos y el usuario de la aplicacion deben existir.
     * <br>Post: La tabla 'asociados' existe y esta vacia, con la estructura correcta.
     */
    public static void limpiarYCrearTabla() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = Conexion.obtenerConexion();
            // Invariante: Conexion obtenida
            assert conn != null && !conn.isClosed() : "La conexion debe ser valida y estar abierta.";

            stmt = conn.createStatement();
            
            // Eliminar la tabla si existe
            String dropTableSQL = "DROP TABLE IF EXISTS asociados;";
            stmt.executeUpdate(dropTableSQL);
            
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
     * * @throws SQLException si ocurre un error al insertar los asociados
     * <br>Pre: La tabla 'asociados' debe existir.
     * <br>Post: Se insertan exactamente 3 registros nuevos en la tabla 'asociados'.
     */
    public static void insertarAsociados() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO asociados (nombre, dni, alta) VALUES (?, ?, ?)";
        
        try {
            conn = obtenerConexion();
            // Invariante: Conexion obtenida
            assert conn != null && !conn.isClosed() : "La conexion debe ser valida y estar abierta.";

            ps = conn.prepareStatement(sql);
            int rowsAffected = 0;
            
            // Primer asociado
            ps.setString(1, "Juan Perez");
            ps.setString(2, "12345678A");
            ps.setBoolean(3, true);
            rowsAffected += ps.executeUpdate();
            
            // Segundo asociado
            ps.setString(1, "Maria Garcia");
            ps.setString(2, "87654321B");
            ps.setBoolean(3, true);
            rowsAffected += ps.executeUpdate();
            
            // Tercer asociado
            ps.setString(1, "Carlos Lopez");
            ps.setString(2, "11223344C");
            ps.setBoolean(3, true);
            rowsAffected += ps.executeUpdate();
            
            // Postcondicion
            assert rowsAffected == 3 : "Se esperaba insertar exactamente 3 asociados.";

            System.out.println("3 asociados insertados exitosamente.");
            
        } catch (SQLException e) {
            System.out.println("Error al insertar asociados: " + e.getMessage());
            throw e;
        } finally {
            cerrarRecursos(null, ps, conn);
        }
    }
   
    /**
     * Cierra silenciosamente los recursos JDBC utilizados en las operaciones.
     *
     * @param rs conjunto de resultados a cerrar
     * <br>Pre: No aplica
     * <br>Post: Si rs != null y esta abierto, se cierra sin lanzar excepcion.
     * @param stmt sentencia ejecutada
     * <br>Pre: No aplica
     * <br>Post: Si stmt != null y esta abierto, se cierra sin lanzar excepcion.
     * @param conn conexion a liberar
     * <br>Pre: No aplica
     * <br>Post: Si conn != null y esta abierto, se cierra sin lanzar excepcion.
     */
    public static void cerrarRecursos(ResultSet rs, Statement stmt, Connection conn) {
        // No se pueden usar asserts para garantizar el cierre, ya que es una operacion silenciosa (try/catch).

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
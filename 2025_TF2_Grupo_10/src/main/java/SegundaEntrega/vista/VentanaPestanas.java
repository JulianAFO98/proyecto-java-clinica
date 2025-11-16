package SegundaEntrega.vista;

import javax.swing.*;

import SegundaEntrega.modelo.Datos.Asociado;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Ventana principal dividida en pestanas para administrar asociados y simular llamados.
 */
public class VentanaPestanas extends JFrame implements IVista, KeyListener, ListSelectionListener {

    // --- Atributos de Instancia (Componentes Accesibles) ---
    private JTabbedPane tabbedPane;

    // Componentes de la Pestaña Asociados
    public JPanel panelAsociados;
    public JTextField campoNombre;
    public JTextField campoDni;
    public JButton btnCrearAsociado;
    public JButton btnDarBajaAsociado;
    public JTextArea logAreaAsociados;
    public JList<Asociado> listaAsociados;
    public DefaultListModel<Asociado> listaModeloAsociados; // Modelo para manipular la lista

    // Componentes de la Pestaña Simulación
    public JPanel panelSimulacion;
    public JButton btnIniciar;
    public JButton btnDetener;
    public JButton btnOperario;
    public JTextArea logAreaSimulacion;
    public JTextField campoCantidad;
    public JLabel textoCantidad;
    public JTextField campoIteracion;
    public JLabel textoIteraciones;

    /**
     * Construye la ventana configurando ambas pestanas y sus componentes.
     * <br>Pre: No aplica.
     * <br>Post: La ventana se inicializa, es visible, contiene dos pestanas y todos los componentes internos son no nulos.
     */
    public VentanaPestanas() {
        setTitle("Gestion de Asociados y Simulacion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // 1. Crear el JTabbedPane
        tabbedPane = new JTabbedPane();

        // 2. Crear y configurar la primera pestaña: Asociados
        panelAsociados = crearPanelAsociados(); // Ahora se asigna al atributo
        tabbedPane.addTab("Asociados", panelAsociados);

        // 3. Crear y configurar la segunda pestaña: Simulación
        panelSimulacion = crearPanelSimulacion(); // Ahora se asigna al atributo
        tabbedPane.addTab("Simulacion", panelSimulacion);

        // 4. Agregar el JTabbedPane a la ventana principal
        add(tabbedPane);
        setVisible(true);
        
        // Postcondicion basica (control de componentes principales)
        assert tabbedPane != null : "El panel de pestanas no debe ser nulo.";
        assert panelAsociados != null : "El panel de asociados no debe ser nulo.";
        assert panelSimulacion != null : "El panel de simulacion no debe ser nulo.";
    }

    

    // --- Metodo para crear el Panel Asociados ---
    /**
     * Crea el panel de administracion de asociados.
     *
     * @return panel configurado con formulario, lista y log
     * <br>Pre: No aplica
     * <br>Post: Retorna un JPanel no nulo y asigna valores no nulos a todos los atributos de la pestana Asociados.
     */
    private JPanel crearPanelAsociados() {
        // ... (Implementacion del metodo es larga y no se modifica la logica interna)
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Panel de Formulario y Lista (Norte)
        JPanel panelNorte = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Asociado"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panelFormulario.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        campoNombre = new JTextField(15);
        campoNombre.setFocusable(true);
        campoNombre.addKeyListener(this);
        panelFormulario.add(campoNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelFormulario.add(new JLabel("DNI:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        campoDni = new JTextField(15);
        campoDni.setFocusable(true);
        campoDni.addKeyListener(this);
        panelFormulario.add(campoDni, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        btnCrearAsociado = new JButton("Crear Asociado");
        btnCrearAsociado.setEnabled(false);
        panelFormulario.add(btnCrearAsociado, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        btnDarBajaAsociado = new JButton("Dar baja asociado");
        btnDarBajaAsociado.setEnabled(false);
        panelFormulario.add(btnDarBajaAsociado, gbc);
        panelNorte.add(panelFormulario);

        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Lista de Asociados"));
        listaModeloAsociados = new DefaultListModel<>(); // Se crea el modelo (atributo)
        listaAsociados = new JList<Asociado>(listaModeloAsociados); // Se usa el modelo en la lista (atributo)
        JScrollPane scrollLista = new JScrollPane(listaAsociados);
        panelLista.add(scrollLista, BorderLayout.CENTER);

        panelNorte.add(panelLista);

        panel.add(panelNorte, BorderLayout.NORTH);

        logAreaAsociados = new JTextArea(5, 40); // Ahora se asigna al atributo
        logAreaAsociados.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(logAreaAsociados);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Log de Acciones"));
        panel.add(scrollLog, BorderLayout.CENTER);

        // Postcondicion parcial: verificacion de asignacion de atributos
        assert campoNombre != null : "campoNombre no debe ser nulo.";
        assert listaModeloAsociados != null : "listaModeloAsociados no debe ser nulo.";

        return panel;
    }

    /**
     * Crea el panel de simulacion con sus controles y log.
     *
     * @return panel configurado para la simulacion
     * <br>Pre: No aplica
     * <br>Post: Retorna un JPanel no nulo y asigna valores no nulos a todos los atributos de la pestana Simulacion.
     */
    private JPanel crearPanelSimulacion() {
        // ... (Implementacion del metodo es larga y no se modifica la logica interna)
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnIniciar = new JButton("Iniciar Simulacion");
        btnDetener = new JButton("Detener Simulacion");
        btnOperario = new JButton("Llamar Operario");
        textoCantidad = new JLabel("Asociados:");
        campoCantidad = new JTextField();
        campoCantidad.setColumns(3);
        campoCantidad.addKeyListener(this);
        textoIteraciones = new JLabel("Iteraciones:");
        campoIteracion = new JTextField();
        campoIteracion.setColumns(3);
        campoIteracion.addKeyListener(this);
        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);
        panelBotones.add(btnOperario);
        panelBotones.add(textoCantidad);
        panelBotones.add(campoCantidad);
        panelBotones.add(textoIteraciones);
        panelBotones.add(campoIteracion);
        panel.add(panelBotones, BorderLayout.NORTH);

        logAreaSimulacion = new JTextArea(10, 40);
        logAreaSimulacion.setEditable(false);
        JScrollPane scrollLogSimulacion = new JScrollPane(logAreaSimulacion);
        scrollLogSimulacion.setBorder(BorderFactory.createTitledBorder("Log de Simulacion"));
        panel.add(scrollLogSimulacion, BorderLayout.CENTER);

        // Postcondicion parcial: verificacion de asignacion de atributos
        assert btnIniciar != null : "btnIniciar no debe ser nulo.";
        assert logAreaSimulacion != null : "logAreaSimulacion no debe ser nulo.";

        return panel;
    }

    /**
     * Asocia un listener de acciones a los botones de la pestana Asociados y a la lista.
     * <br>Pre: al != null
     * <br>Post: Los botones y la lista de asociados tienen el listener asociado.
     */
    @Override
    public void addActionListenerAsociado(ActionListener al) {
        // Precondicion
        assert al != null : "El ActionListener no puede ser nulo.";
        
        btnCrearAsociado.setActionCommand(IVista.CREAR_ASOCIADO);
        btnCrearAsociado.addActionListener(al);
        btnDarBajaAsociado.setActionCommand(IVista.DAR_BAJA_ASOCIADO);
        btnDarBajaAsociado.addActionListener(al);
        listaAsociados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        listaAsociados.addListSelectionListener(this);
        
        // Postcondicion: No hay un mecanismo directo para verificar que el listener fue agregado,
        // pero se verifica que los componentes clave son no nulos (Invariante)
        assert btnCrearAsociado.getActionCommand().equals(IVista.CREAR_ASOCIADO) : "El command de crear asociado no se configuro correctamente.";
    }

    /**
     * Asocia un listener de acciones a los botones de la pestana Simulacion.
     * <br>Pre: al != null
     * <br>Post: Los botones de simulacion tienen el listener asociado.
     */
    @Override
    public void addActionListenerSimulacion(ActionListener al) {
        // Precondicion
        assert al != null : "El ActionListener no puede ser nulo.";
        
        btnIniciar.setActionCommand(IVista.EMPEZAR_SIMULACION);
        btnIniciar.addActionListener(al);
        btnDetener.setActionCommand(IVista.FINALIZAR_SIMULACION);
        btnDetener.addActionListener(al);
        btnOperario.setActionCommand(IVista.LLAMAR_OPERARIO);
        btnOperario.addActionListener(al);
        
        // Postcondicion
        assert btnIniciar.getActionCommand().equals(IVista.EMPEZAR_SIMULACION) : "El command de iniciar simulacion no se configuro correctamente.";
    }

    /**
     * Muestra un mensaje emergente.
     *
     * @param s mensaje a mostrar
     * <br>Pre: s != null
     * <br>Post: No hay cambio de estado interno. Se muestra el mensaje.
     */
    @Override
    public void mostrarMensaje(String s) {
        assert s != null : "El mensaje a mostrar no puede ser nulo.";
        JOptionPane.showMessageDialog(this, s);
    }

    /**
     * Obtiene el asociado seleccionado en la lista.
     *
     * @return asociado seleccionado o null
     * <br>Pre: listaAsociados no nula
     * <br>Post: Retorna el valor seleccionado.
     */
    @Override
    public Asociado getAsociadoSeleccionado() {
        assert listaAsociados != null : "La lista de asociados no debe ser nula.";
        return (Asociado) listaAsociados.getSelectedValue();
    }

    /**
     * Obtiene el DNI ingresado en el campo de texto.
     *
     * @return cadena con dni
     * <br>Pre: campoDni no nulo
     * <br>Post: Retorna el texto del campo.
     */
    @Override
    public String getDni() {
        assert campoDni != null : "El campo DNI no debe ser nulo.";
        String dni = campoDni.getText();
        return dni;
    }

    /**
     * Obtiene el nombre ingresado en el campo de texto.
     *
     * @return cadena con nombre
     * <br>Pre: campoNombre no nulo
     * <br>Post: Retorna el texto del campo.
     */
    @Override
    public String getNombre() {
        assert campoNombre != null : "El campo nombre no debe ser nulo.";
        String nombre = campoNombre.getText();
        return nombre;
    }

    /**
     * Obtiene el valor del campo cantidad de asociados a simular.
     *
     * @return cadena con cantidad
     * <br>Pre: campoCantidad no nulo
     * <br>Post: Retorna el texto del campo.
     */
    @Override
    public String getCantidad() {
        assert campoCantidad != null : "El campo cantidad no debe ser nulo.";
        return campoCantidad.getText();
    }

    /**
     * Valida campos numericos y habilita botones segun la entrada.
     *
     * @param e evento de teclado recibido
     * <br>Pre: e != null
     * <br>Post: El estado de los botones btnCrearAsociado, btnIniciar es actualizado.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        assert e != null : "El evento KeyEvent no puede ser nulo.";
        
        Object source = e.getSource();

        if (source == campoNombre || source == campoDni) {
            String nombre = campoNombre.getText().trim();
            String dni = campoDni.getText().trim();
            boolean nombreEsValido = !nombre.isEmpty();
            boolean dniEsValido = false;

            try {
                // Invariante: Solo intentar parsear si hay texto
                if (!dni.isEmpty()) {
                     Integer.parseInt(dni);
                     dniEsValido = true;
                }
            } catch (NumberFormatException ex) {
                dniEsValido = false;
            }

            this.btnCrearAsociado.setEnabled(dniEsValido && nombreEsValido);
            
            // Postcondicion parcial
            assert this.btnCrearAsociado.isEnabled() == (dniEsValido && nombreEsValido) : "El estado del boton Crear Asociado no se actualizo correctamente.";
        }

        if (source == campoCantidad || source == campoIteracion) {
            String cantidad = campoCantidad.getText().trim();
            String iteracion = campoIteracion.getText().trim();
            boolean inputValido = false;
            try {
                int valor = Integer.parseInt(cantidad);
                int iter = Integer.parseInt(iteracion);
                if (valor > 0 && iter > 0) {
                    inputValido = true;
                }
            } catch (NumberFormatException ex) {
                inputValido = false;
            }
            this.btnIniciar.setEnabled(inputValido);
            
            // Postcondicion parcial
            assert this.btnIniciar.isEnabled() == inputValido : "El estado del boton Iniciar Simulacion no se actualizo correctamente.";
        }
    }

    /**
     * Agrega un mensaje al log de asociados.
     *
     * @param s cadena a agregar
     * <br>Pre: s != null, logAreaAsociados no nulo
     * <br>Post: El texto 's' es anexado al log.
     */
    @Override
    public void agregarALogAsociados(String s) {
        assert s != null : "El mensaje de log no puede ser nulo.";
        assert logAreaAsociados != null : "El area de log de asociados no debe ser nula.";
        this.logAreaAsociados.append(s);
    }

    /**
     * Agrega un mensaje al log de simulación.
     *
     * @param s cadena a agregar
     * <br>Pre: s != null, logAreaSimulacion no nulo
     * <br>Post: El texto 's' es anexado al log, seguido de un salto de linea.
     */
    @Override
    public void agregarALogSimulacion(String s) {
        assert s != null : "El mensaje de log no puede ser nulo.";
        assert logAreaSimulacion != null : "El area de log de simulacion no debe ser nula.";
        this.logAreaSimulacion.append(s + "\n");
    }

    /**
     * No se utiliza pero se implementa para cumplir el contrato KeyListener.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        assert e != null : "El evento KeyEvent no puede ser nulo.";
    }

    /**
     * No se utiliza pero se implementa para cumplir el contrato KeyListener.
     *
     * @param e evento de teclado ignorado
     */
    @Override
    public void keyPressed(KeyEvent e) {
        assert e != null : "El evento KeyEvent no puede ser nulo.";
    }

    /**
     * Refresca el contenido de la lista de asociados.
     *
     * @param asociados registros a mostrar
     * <br>Pre: asociados != null, listaModeloAsociados no nulo.
     * <br>Post: listaModeloAsociados contiene los elementos de 'asociados' y esta limpio previamente.
     */
    @Override
    public void actualizarListas(List<Asociado> asociados) {
        // Precondicion
        assert asociados != null : "La lista de asociados no puede ser nula.";
        assert listaModeloAsociados != null : "El modelo de la lista no debe ser nulo.";
        
        int tamanoAnterior = listaModeloAsociados.getSize();
        
        listaModeloAsociados.clear();
        
        // Invariante
        assert listaModeloAsociados.isEmpty() : "El modelo de la lista debe estar vacio despues de clear().";
        
        for (Asociado asociado : asociados) {
            assert asociado != null : "Un Asociado en la lista de entrada no debe ser nulo.";
            listaModeloAsociados.addElement(asociado);
        }
        
        // Postcondicion
        assert listaModeloAsociados.getSize() == asociados.size() : "El tamano final del modelo debe coincidir con el tamano de la lista de entrada.";
    }

    

    /**
     * Limpia los campos del formulario de asociados y resetea botones.
     * <br>Pre: Los componentes del formulario son no nulos.
     * <br>Post: Los campos de texto estan vacios y los botones de accion estan deshabilitados.
     */
    public void limpiarCamposAsociado() {
        assert campoNombre != null : "El campo nombre no debe ser nulo.";
        assert campoDni != null : "El campo DNI no debe ser nulo.";
        assert btnCrearAsociado != null : "El boton crear asociado no debe ser nulo.";
        assert btnDarBajaAsociado != null : "El boton dar de baja asociado no debe ser nulo.";

        campoNombre.setText("");
        campoDni.setText("");
        btnCrearAsociado.setEnabled(false);
        btnDarBajaAsociado.setEnabled(false);
        
        // Postcondicion
        assert campoNombre.getText().isEmpty() : "El campo nombre debe estar vacio.";
        assert !btnCrearAsociado.isEnabled() : "El boton crear asociado debe estar deshabilitado.";
    }

    /**
     * Gestiona la seleccion de la lista para habilitar acciones.
     *
     * @param e evento de cambio de seleccion
     * <br>Pre: e != null, listaAsociados no nula.
     * <br>Post: El estado de los botones de accion de asociados se actualiza en funcion de si hay un elemento seleccionado.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        assert e != null : "El evento de seleccion no puede ser nulo.";
        assert listaAsociados != null : "La lista de asociados no debe ser nula.";
        
        if (e.getSource() == listaAsociados) {
            if (!e.getValueIsAdjusting()) {
                Asociado seleccionado = listaAsociados.getSelectedValue();
                
                // Postcondicion: Si hay seleccionado, DarBaja se habilita.
                if (seleccionado != null) {
                    btnDarBajaAsociado.setEnabled(true);
                    btnCrearAsociado.setEnabled(false);
                    assert btnDarBajaAsociado.isEnabled() : "El boton Dar Baja debe estar habilitado al seleccionar un elemento.";
                } else {
                    btnDarBajaAsociado.setEnabled(false);
                    // IMPORTANTE: Al deseleccionar, btnCrearAsociado debe depender de keyReleased, no de valueChanged.
                    // Se deja en false para que solo se habilite al escribir.
                }
            }
        }
    }

    /**
     * Habilita o deshabilita los campos de carga segun el estado de la simulacion.
     *
     * @param estado true para habilitar entradas
     * <br>Pre: Los componentes del formulario son no nulos.
     * <br>Post: Los campos DNI y Nombre estan habilitados/deshabilitados segun 'estado'.
     */
    @Override
    public void cambiarEstadoInput(boolean estado) {
        assert campoDni != null : "El campo DNI no debe ser nulo.";
        assert campoNombre != null : "El campo nombre no debe ser nulo.";

        this.campoDni.setText("");
        this.campoNombre.setText("");
        this.btnCrearAsociado.setEnabled(false);
        this.btnDarBajaAsociado.setEnabled(false);
        this.campoDni.setEnabled(estado);
        this.campoNombre.setEnabled(estado);
        
        // Postcondicion
        assert this.campoDni.isEnabled() == estado : "El estado del campo DNI no se configuro correctamente.";
        assert this.campoNombre.isEnabled() == estado : "El estado del campo nombre no se configuro correctamente.";
    }

    /**
     * Obtiene el valor del campo de texto de iteraciones.
     *
     * @return cadena con numero de iteraciones
     * <br>Pre: campoIteracion no nulo
     * <br>Post: Retorna el texto del campo.
     */
    @Override
    public String getIteracion() {
        assert campoIteracion != null : "El campo iteracion no debe ser nulo.";
        return campoIteracion.getText();
    }

}
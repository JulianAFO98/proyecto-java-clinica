package SegundaEntrega.vista;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPestanas extends JFrame {

    // --- Atributos de Instancia (Componentes Accesibles) ---
    private JTabbedPane tabbedPane;
    
    // Componentes de la Pestaña Asociados
    public JPanel panelAsociados;
    public JTextField campoNombre;
    public JTextField campoDni;
    public JButton btnCrearAsociado;
    public JTextArea logAreaAsociados;
    public JList<String> listaAsociados;
    public DefaultListModel<String> listaModeloAsociados; // Modelo para manipular la lista

    // Componentes de la Pestaña Simulación
    public JPanel panelSimulacion;
    public JButton btnIniciar;
    public JButton btnDetener;
    public JTextArea logAreaSimulacion;
    // --------------------------------------------------------

    public VentanaPestanas() {
        setTitle("Gestión de Asociados y Simulación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // 1. Crear el JTabbedPane
        tabbedPane = new JTabbedPane();

        // 2. Crear y configurar la primera pestaña: Asociados
        panelAsociados = crearPanelAsociados(); // Ahora se asigna al atributo
        tabbedPane.addTab("Asociados", panelAsociados);

        // 3. Crear y configurar la segunda pestaña: Simulación
        panelSimulacion = crearPanelSimulacion(); // Ahora se asigna al atributo
        tabbedPane.addTab("Simulación", panelSimulacion);

        // 4. Agregar el JTabbedPane a la ventana principal
        add(tabbedPane);
        
        // 5. Agregar Listeners (Podrían ir aquí o en un método aparte)
        configurarListeners();
        
        setVisible(true);
    }

    private void configurarListeners() {
        // Ejemplo de Listener para el botón Crear Asociado
        btnCrearAsociado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String dni = campoDni.getText();
                
                // 1. Actualizar Log
                logAreaAsociados.append("Asociado creado: " + nombre + " con DNI: " + dni + "\n");
                
                // 2. Actualizar Lista
                listaModeloAsociados.addElement(nombre + " (" + dni + ")");
                
                // 3. Limpiar campos
                campoNombre.setText("");
                campoDni.setText("");
            }
        });
        
        // Listener para Iniciar Simulación
        btnIniciar.addActionListener(e -> logAreaSimulacion.append("Simulación iniciada...\n"));
        
        // Listener para Detener Simulación
        btnDetener.addActionListener(e -> logAreaSimulacion.append("Simulación detenida.\n"));
    }

    // --- Método para crear el Panel Asociados ---
    private JPanel crearPanelAsociados() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Panel de Formulario y Lista (Norte)
        JPanel panelNorte = new JPanel(new GridLayout(1, 2, 10, 10));
        
        // 1. Panel de Formulario (Izquierda del Norte)
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Asociado"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre (Ahora se asigna al atributo)
        gbc.gridx = 0; gbc.gridy = 0; panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; campoNombre = new JTextField(15); panelFormulario.add(campoNombre, gbc);
        
        // DNI (Ahora se asigna al atributo)
        gbc.gridx = 0; gbc.gridy = 1; panelFormulario.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; campoDni = new JTextField(15); panelFormulario.add(campoDni, gbc);

        // Botón Crear Asociado (Ahora se asigna al atributo)
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; btnCrearAsociado = new JButton("Crear Asociado"); panelFormulario.add(btnCrearAsociado, gbc);
        
        panelNorte.add(panelFormulario);

        // 2. Lista de Elementos (Derecha del Norte)
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Lista de Asociados"));
        listaModeloAsociados = new DefaultListModel<>(); // Se crea el modelo (atributo)
        listaAsociados = new JList<>(listaModeloAsociados); // Se usa el modelo en la lista (atributo)
        JScrollPane scrollLista = new JScrollPane(listaAsociados);
        panelLista.add(scrollLista, BorderLayout.CENTER);
        
        panelNorte.add(panelLista);
        
        panel.add(panelNorte, BorderLayout.NORTH);

        // 3. Log (Centro)
        logAreaAsociados = new JTextArea(5, 40); // Ahora se asigna al atributo
        logAreaAsociados.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(logAreaAsociados);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Log de Acciones"));
        panel.add(scrollLog, BorderLayout.CENTER);
        
        return panel;
    }

    private JPanel crearPanelSimulacion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Panel de Botones (Norte)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnIniciar = new JButton("Iniciar Simulación"); 
        btnDetener = new JButton("Detener Simulación"); 
        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);
        panel.add(panelBotones, BorderLayout.NORTH);

        // Log (Centro)
        logAreaSimulacion = new JTextArea(10, 40); 
        logAreaSimulacion.setEditable(false);
        JScrollPane scrollLogSimulacion = new JScrollPane(logAreaSimulacion);
        scrollLogSimulacion.setBorder(BorderFactory.createTitledBorder("Log de Simulación"));
        panel.add(scrollLogSimulacion, BorderLayout.CENTER);

        return panel;
    }
}
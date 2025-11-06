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
    public JTextArea logAreaSimulacion;

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

        setVisible(true);
    }

    // --- Método para crear el Panel Asociados ---
    private JPanel crearPanelAsociados() {
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

        return panel;
    }

    private JPanel crearPanelSimulacion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnIniciar = new JButton("Iniciar Simulación");
        btnDetener = new JButton("Detener Simulación");
        panelBotones.add(btnIniciar);
        panelBotones.add(btnDetener);
        panel.add(panelBotones, BorderLayout.NORTH);

        logAreaSimulacion = new JTextArea(10, 40);
        logAreaSimulacion.setEditable(false);
        JScrollPane scrollLogSimulacion = new JScrollPane(logAreaSimulacion);
        scrollLogSimulacion.setBorder(BorderFactory.createTitledBorder("Log de Simulación"));
        panel.add(scrollLogSimulacion, BorderLayout.CENTER);

        return panel;
    }

    @Override
    public void addActionListenerAsociado(ActionListener al) {
        btnCrearAsociado.setActionCommand(this.CREAR_ASOCIADO);
        btnCrearAsociado.addActionListener(al);
        btnDarBajaAsociado.setActionCommand(this.DAR_BAJA_ASOCIADO);
        btnDarBajaAsociado.addActionListener(al);
        listaAsociados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // <-- nuevo
        listaAsociados.addListSelectionListener(this);
    }

    @Override
    public void addActionListenerSimulacion(ActionListener al) {
        btnIniciar.setActionCommand(this.EMPEZAR_SIMULACION);
        btnIniciar.addActionListener(al);
        btnDetener.setActionCommand(this.FINALIZAR_SIMULACION);
        btnDetener.addActionListener(al);
    }

    @Override
    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    @Override
    public Asociado getAsociadoSeleccionado() {
        return (Asociado) listaAsociados.getSelectedValue();
    }

    @Override
    public String getDni() {
        String dni = campoDni.getText();
        return dni;
    }

    @Override
    public String getNombre() {
        String nombre = campoNombre.getText();
        return nombre;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        String nombre = campoNombre.getText().trim();
        String dni = campoDni.getText().trim();
        boolean dniEsValido = false;
        boolean nombreEsValido = !nombre.isEmpty();
        try {
            Integer.parseInt(dni);
            dniEsValido = true;
        } catch (NumberFormatException ex) {
            dniEsValido = false;
        }

        if (dni.isEmpty()) {
            dniEsValido = false;
        }
        this.btnCrearAsociado.setEnabled(dniEsValido && nombreEsValido);
    }

    @Override
    public void agregarALogAsociados(String s) {
        this.logAreaAsociados.append(s);
    }

    @Override
    public void agregarALogSimulacion(String s) {
        this.logAreaSimulacion.append(s);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void actualizarListas(List<Asociado> asociados) {
        listaModeloAsociados.clear();
        for (Asociado asociado : asociados) {
            listaModeloAsociados.addElement(asociado);
        }

    }

    public void limpiarCamposAsociado() {
        campoNombre.setText("");
        campoDni.setText("");
        btnCrearAsociado.setEnabled(false);
        btnDarBajaAsociado.setEnabled(false);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == listaAsociados) {
            if (!e.getValueIsAdjusting()) {
                Asociado seleccionado = listaAsociados.getSelectedValue();
                System.out.println("Seleccionado: " + seleccionado);
                if (seleccionado != null) {
                    btnDarBajaAsociado.setEnabled(true);
                    btnCrearAsociado.setEnabled(false);
                } else {
                    btnDarBajaAsociado.setEnabled(false);
                    btnCrearAsociado.setEnabled(true);
                }
            }
        }
    }

}
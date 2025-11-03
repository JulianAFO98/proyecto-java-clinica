package SegundaEntrega.modelo.Datos;

import SegundaEntrega.persistencia.AsociadoDAO;
import SegundaEntrega.persistencia.PersistenciaAsociado;

/*
 * Clase facade actual
 */
public class GestionLlamados {
    private Ambulancia ambulancia;
    private Operario ope;
    private AsociadoDAO asociadoDao;

    

    public GestionLlamados() {
        this.ambulancia = new Ambulancia();
        this.ope = new Operario();
        this.asociadoDao = new PersistenciaAsociado();
    }



    public void agregarAsociado(String nombre, String dni) {
        
    }
}

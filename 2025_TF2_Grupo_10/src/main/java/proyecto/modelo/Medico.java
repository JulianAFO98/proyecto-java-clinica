package proyecto.modelo;

import proyecto.modelo.interfaces.IMedico;

/**
 * Clase medico que extiende de una persona, implementa el comportamiento de un IMedico,
 * define los atributos necesarios para que sea un medico
 */

public class Medico extends Persona implements IMedico{
    /** 
     * Numero de matricula del medico
     */
    protected int numeroMatricula;
    /** 
     * Sueldo base del medico
     */
    protected double sueldoBase;
    //protected boolean isDisponible; todo
    public Medico(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,int numeroMatricula, double sueldoBase) {
        super(dni, nombre, apellido, ciudad, telefono, domicilio);
        this.numeroMatricula = numeroMatricula;
        this.sueldoBase = sueldoBase;
    }
    @Override
    /** 
     * @return Devuelve el sueldo correcto del medico
     */
    public double calcularSueldo() {
        return sueldoBase;
    }
    @Override
    /** 
     * @return Devuelve la especialidad correcta del medico,para el medico basico es null
     */
    public String getEspecialidad() {
        return null;
    }
    @Override
    public int getNumeroMatricula() {
        return numeroMatricula;
    }



}

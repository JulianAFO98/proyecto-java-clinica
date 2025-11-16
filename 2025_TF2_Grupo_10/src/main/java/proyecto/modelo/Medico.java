package proyecto.modelo;

import proyecto.modelo.interfaces.IMedico;

/**
 * Clase medico que extiende de una persona, implementa el comportamiento de un IMedico,
 * define los atributos necesarios para que sea un medico
 */

public class Medico extends Persona implements IMedico{
    /** * Numero de matricula del medico
     */
    protected int numeroMatricula;
    /** * Sueldo base del medico
     */
    protected double sueldoBase;
    //protected boolean isDisponible; todo
    public Medico(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,int numeroMatricula, double sueldoBase) {
        super(dni, nombre, apellido, ciudad, telefono, domicilio);
        
        assert numeroMatricula > 0 : "El numero de matricula debe ser positivo.";
        assert sueldoBase >= 0 : "El sueldo base no puede ser negativo.";
        
        this.numeroMatricula = numeroMatricula;
        this.sueldoBase = sueldoBase;
        
        assert this.numeroMatricula == numeroMatricula : "El numero de matricula no se asigno correctamente.";
        assert this.sueldoBase == sueldoBase : "El sueldo base no se asigno correctamente.";
    }
    
    @Override
    /** * @return Devuelve el sueldo correcto del medico
     */
    public double calcularSueldo() {
        double sueldoCalculado = sueldoBase;
        
        assert sueldoCalculado >= 0 : "El sueldo calculado no puede ser negativo.";
        assert sueldoCalculado == sueldoBase : "El sueldo calculado debe ser igual al sueldo base para un Medico base.";
        
        return sueldoCalculado;
    }
    
    @Override
    /** * @return Devuelve la especialidad correcta del medico,para el medico basico es null
     */
    public String getEspecialidad() {
        return null;
    }
    
    @Override
    public int getNumeroMatricula() {
        assert numeroMatricula > 0 : "El numero de matricula no debe ser negativo o cero.";
        return numeroMatricula;
    }


}
package proyecto.modelo;

import proyecto.modelo.interfaces.IMedico;

public class Medico extends Persona implements IMedico{
    protected int numeroMatricula;
    protected double sueldoBase;

    public Medico(String dni, String nombre, String apellido, String ciudad, String telefono, Domicilio domicilio,int numeroMatricula, double sueldoBase) {
        super(dni, nombre, apellido, ciudad, telefono, domicilio);
        this.numeroMatricula = numeroMatricula;
        this.sueldoBase = sueldoBase;
    }
    @Override
    public double calcularSueldo() {
        return sueldoBase;
    }
    @Override
    public String getEspecialidad() {
        return null;
    }
    @Override
    public int getNumeroMatricula() {
        return numeroMatricula;
    }



}

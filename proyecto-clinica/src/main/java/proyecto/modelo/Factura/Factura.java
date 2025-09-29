package proyecto.modelo.Factura;

import java.util.Date;

import proyecto.modelo.Habitacion.Habitacion;
import proyecto.modelo.paciente.Paciente;

public class Factura {
    private static int contadorOrdenFactura = 0;
    private int ordenFactura;
    private String nombreCliente;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private int cantidadDeDias;
    private Paciente paciente;
    Habitacion h;


    public Factura(Paciente p,String nombreCliente, Date fechaIngreso, Date fechaEgreso, int cantidadDeDias, Habitacion h) {
        this.paciente =p;
        this.nombreCliente = nombreCliente;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgreso = fechaEgreso;
        this.cantidadDeDias = cantidadDeDias;
        this.h = h;
        this.ordenFactura=contadorOrdenFactura++;
    }


    public int getOrdenFactura() {
        return ordenFactura;
    }


    public String getNombreCliente() {
        return nombreCliente;
    }


    public Date getFechaIngreso() {
        return fechaIngreso;
    }


    public Date getFechaEgreso() {
        return fechaEgreso;
    }


    public int getCantidadDeDias() {
        return cantidadDeDias;
    }


    public Habitacion getH() {
        return h;
    }


    @Override
    public String toString() {
        return "N°Factura:" + this.ordenFactura+"\n"
        +"Nombre Paciente: "+this.paciente.getNombre()+"\n"
        +"Fecha Ingreso:";
    }
    
    

}

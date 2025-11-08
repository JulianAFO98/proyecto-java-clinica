package SegundaEntrega.Patrones.PatronState;

import java.util.Observable;

import SegundaEntrega.modelo.Datos.Ambulancia;

public class AtendiendoADomicilio  implements EstadoAmbulancia {
    private Ambulancia ambulancia;

    public AtendiendoADomicilio(Ambulancia ambulancia) {
        this.ambulancia = ambulancia;
    }

    @Override
    public void solicitarAtencionDomicilio() {
    }

    @Override
    public void solicitarTrasladoClinica() {
        ambulancia.notificarCambio("No puede solicitar traslado mientras atiende a domicilio");
       
    }

    @Override
    public void retornoAutomatico() {
        ambulancia.notificarCambio("Regresando sin paciente");
        ambulancia.setEstado(new RegresandoSinPaciente(ambulancia));
    }

    @Override
    public void solicitarMantenimiento() {
        ambulancia.notificarCambio("No puede solicitar mantenimiento mientras atiende a domicilio");
       
    }
}

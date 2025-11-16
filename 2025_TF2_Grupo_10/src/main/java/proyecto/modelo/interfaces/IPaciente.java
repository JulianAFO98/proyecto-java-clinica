package proyecto.modelo.interfaces;
import proyecto.modelo.paciente.Paciente;
import proyecto.modelo.paciente.Nino;
import proyecto.modelo.paciente.Joven;
import proyecto.modelo.paciente.Mayor;

/**
 * Interfaz que define el comportamiento de un paciente en el sistema.
 */
public interface IPaciente {
	abstract Paciente decidirSala(Paciente otro);
	abstract Paciente prioridadCon(Nino n);
	abstract Paciente prioridadCon(Joven j);
	abstract Paciente prioridadCon(Mayor m);
}

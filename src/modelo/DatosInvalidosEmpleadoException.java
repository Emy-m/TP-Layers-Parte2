package modelo;

public class DatosInvalidosEmpleadoException extends Exception {
	public DatosInvalidosEmpleadoException(String detalle) {
		super("Dato invalido para un empleado: " + detalle);
	}
}

package modelo;

public class DatosInvalidosEmpleadoException extends RuntimeException {
	public DatosInvalidosEmpleadoException(String detalle) {
		super("Dato invalido para un empleado: " + detalle);
	}
}
